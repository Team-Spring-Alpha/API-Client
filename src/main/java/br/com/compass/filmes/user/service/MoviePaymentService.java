package br.com.compass.filmes.user.service;

import br.com.compass.filmes.user.client.GatewayProxy;
import br.com.compass.filmes.user.client.MovieSearchProxy;
import br.com.compass.filmes.user.dto.allocation.history.request.RequestAllocationDTO;
import br.com.compass.filmes.user.dto.allocation.history.request.RequestAllocationMovieDTO;
import br.com.compass.filmes.user.dto.movie.ResponseMovieByIdDTO;
import br.com.compass.filmes.user.dto.movie.ResponseRentAndBuyDTO;
import br.com.compass.filmes.user.dto.moviepayment.RequestMoviePaymentDTO;
import br.com.compass.filmes.user.dto.moviepayment.RequestRentOrBuyDTO;
import br.com.compass.filmes.user.dto.payment.request.RequestPaymentCreditCardDTO;
import br.com.compass.filmes.user.dto.payment.request.RequestPaymentCustomerDTO;
import br.com.compass.filmes.user.dto.payment.request.RequestPaymentDTO;
import br.com.compass.filmes.user.dto.payment.response.*;
import br.com.compass.filmes.user.entities.CreditCardEntity;
import br.com.compass.filmes.user.entities.UserEntity;
import br.com.compass.filmes.user.enums.MovieLinks;
import br.com.compass.filmes.user.enums.PaymentVendorEnum;
import br.com.compass.filmes.user.exceptions.*;
import br.com.compass.filmes.user.producer.MessageHistoryProducer;
import br.com.compass.filmes.user.repository.UserRepository;
import br.com.compass.filmes.user.util.EncriptPasswordUtil;
import br.com.compass.filmes.user.util.ValidateRequestMoviePaymentUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MoviePaymentService {

    private LocalTime tokenExpirationTime;
    private String authToken;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final MovieSearchProxy movieSearchProxy;
    private final GatewayProxy gatewayProxy;
    private final MessageHistoryProducer messageHistoryProducer;
    private final EncriptPasswordUtil encriptPasswordUtil;
    private final ValidateRequestMoviePaymentUtil validateRequestMoviePaymentUtil;

    public ResponseGatewayReprovedDTO post(String emailUser, RequestMoviePaymentDTO requestMoviePaymentDTO) {
        validateRequestMoviePaymentUtil.validRequestMoviePayment(requestMoviePaymentDTO);
        UserEntity userEntity = userRepository.findByEmail(emailUser).orElseThrow(() -> new UserNotFoundException("userEmail: " + emailUser));
        CreditCardEntity creditCard = getCreditCard(requestMoviePaymentDTO, userEntity);

        List<ResponseMoviePaymentProcessDTO> moviePaymentProcessList = new ArrayList<>();
        Double amount = 0.0;

        if (requestMoviePaymentDTO.getMoviesBuy() != null) {
            amount = processTheBuyMovieList(requestMoviePaymentDTO, moviePaymentProcessList, amount);
        }

        if (requestMoviePaymentDTO.getMoviesRent() != null) {
            amount = processTheRentMovieList(requestMoviePaymentDTO, moviePaymentProcessList, amount);
        }

        PaymentVendorEnum randomPaymentVendorEnum = PaymentVendorEnum.getRandomClientEnum();
        if (this.tokenExpirationTime == null) {
            getToken(randomPaymentVendorEnum);
        }

        if (LocalTime.now().isAfter(tokenExpirationTime)) {
            getToken(randomPaymentVendorEnum);
        }


        RequestPaymentCustomerDTO paymentCustomer = buildCustomer(userEntity);

        RequestPaymentDTO requestPaymentDTO = buildRequesPayment(creditCard, amount, randomPaymentVendorEnum, paymentCustomer);

        ResponsePaymentDTO payment = gatewayProxy.getPayment(this.authToken, requestPaymentDTO);

        List<RequestAllocationMovieDTO> requestAllocationMovieDTOList = getRequestAllocationMovies(moviePaymentProcessList);
        RequestAllocationDTO requestAllocationDTO = buildRequestAllocation(requestMoviePaymentDTO, userEntity, payment, requestAllocationMovieDTOList);
        messageHistoryProducer.sendMessage(requestAllocationDTO);

        if (payment.getStatus().equals("REPROVED")) {
            String cause = payment.getAuthorization().getReasonMessage();
            return new ResponseGatewayReprovedDTO(cause);
        }

        return new ResponseGatewayOkDTO(moviePaymentProcessList);
    }

    private Double processTheRentMovieList(RequestMoviePaymentDTO requestMoviePaymentDTO, List<ResponseMoviePaymentProcessDTO> moviePaymentProcessList, Double amount) {
        for (int i = 0; i < requestMoviePaymentDTO.getMoviesRent().size(); i++) {
            RequestRentOrBuyDTO request = requestMoviePaymentDTO.getMoviesRent().get(i);
            Long movieId = request.getId();
            String movieStore = request.getStore();

            ResponseMovieByIdDTO proxyMovieById = movieSearchProxy.getMovieById(movieId);
            String movieTitle = proxyMovieById.getMovieName();
            try {
                buildMoviesProcessList(movieId, movieTitle, movieStore, moviePaymentProcessList);
                Double rentPrice = getThePriceFromTheStore(proxyMovieById.getJustWatch().getRent(), movieStore, movieId);;
                amount += rentPrice;
            } catch (NullPointerException exception) {
                throw new RentMovieNotFoundException("movie id: "+ movieId);
            }
        }
        return amount;
    }

    private Double processTheBuyMovieList(RequestMoviePaymentDTO requestMoviePaymentDTO, List<ResponseMoviePaymentProcessDTO> moviePaymentProcessList, Double amount) {
        for (int i = 0; i < requestMoviePaymentDTO.getMoviesBuy().size(); i++) {
            RequestRentOrBuyDTO request = requestMoviePaymentDTO.getMoviesBuy().get(i);
            Long movieId = request.getId();
            String movieStore = request.getStore();

            ResponseMovieByIdDTO proxyMovieById = movieSearchProxy.getMovieById(movieId);
            String movieTitle = proxyMovieById.getMovieName();

            try {
                buildMoviesProcessList(movieId, movieTitle, movieStore, moviePaymentProcessList);
                Double buyPrice = getThePriceFromTheStore(proxyMovieById.getJustWatch().getBuy(), movieStore, movieId);
                amount += buyPrice;
            } catch (NullPointerException exception) {
                throw new BuyMovieNotFoundException("movie id: " + movieId);
            }
        }
        return amount;
    }

    private Double getThePriceFromTheStore(List<ResponseRentAndBuyDTO> rentAndBuyList, String movieStore, Long movieId) {
        for (ResponseRentAndBuyDTO responseRentAndBuyDTO : rentAndBuyList) {
            if (responseRentAndBuyDTO.getStore().equals(movieStore)) {
                return responseRentAndBuyDTO.getPrice();
            }
        }
        throw new StoreNotFoundException("Store: " + movieStore + " isnt selling the movie: " + movieId);
    }

    private MovieLinks getMovieLinkFromlabel(String label) {
        return MovieLinks.valueOfLabel(label);
    }

    private void buildMoviesProcessList(Long movieId, String movieTitle, String movieStore, List<ResponseMoviePaymentProcessDTO> moviePaymentProcessList) {
        MovieLinks movieLinks = getMovieLinkFromlabel(movieStore);
        ResponseMoviePaymentProcessDTO moviePaymentProcess = new ResponseMoviePaymentProcessDTO(movieId, movieTitle, movieLinks.getLink(), movieStore);
        moviePaymentProcessList.add(moviePaymentProcess);
    }
    private List<RequestAllocationMovieDTO> getRequestAllocationMovies(List<ResponseMoviePaymentProcessDTO> response) {
        List<RequestAllocationMovieDTO> requestAllocationMovieDTOList = new ArrayList<>();
        for (ResponseMoviePaymentProcessDTO responseMoviePaymentProcessDTO : response) {
            RequestAllocationMovieDTO allocationMovie = new RequestAllocationMovieDTO(responseMoviePaymentProcessDTO.getMovieId(), responseMoviePaymentProcessDTO.getTitle());
            requestAllocationMovieDTOList.add(allocationMovie);
        }
        return requestAllocationMovieDTOList;
    }

    private RequestAllocationDTO buildRequestAllocation(RequestMoviePaymentDTO requestMoviePaymentDTO, UserEntity userEntity, ResponsePaymentDTO payment, List<RequestAllocationMovieDTO> requestAllocationMovieDTOList) {
        String userId = userEntity.getId();
        String cardNumber = requestMoviePaymentDTO.getCreditCardNumber();
        String status = payment.getStatus();
        return new RequestAllocationDTO(userId, cardNumber, requestAllocationMovieDTOList, status);
    }

    private void getToken(PaymentVendorEnum randomPaymentVendorEnum) {
        ResponseAuthDTO authToken = gatewayProxy.getAuthToken(randomPaymentVendorEnum);
        this.tokenExpirationTime = LocalTime.now().plusSeconds(Long.parseLong(authToken.getExpiresIn()));
        this.authToken = authToken.getToken();
    }

    private RequestPaymentDTO buildRequesPayment(CreditCardEntity creditCard, Double amount, PaymentVendorEnum randomPaymentVendorEnum, RequestPaymentCustomerDTO paymentCustomer) {
        RequestPaymentDTO requestPaymentDTO = new RequestPaymentDTO();
        requestPaymentDTO.setSellerId(randomPaymentVendorEnum.getSellerId());
        requestPaymentDTO.setCustomer(paymentCustomer);
        requestPaymentDTO.setTransactionAmount(amount);
        RequestPaymentCreditCardDTO requestCreditCard = modelMapper.map(creditCard, RequestPaymentCreditCardDTO.class);
        requestCreditCard.setNumber(encriptPasswordUtil.Encript(creditCard.getNumber()));
        requestPaymentDTO.setCard(requestCreditCard);
        return requestPaymentDTO;
    }

    private CreditCardEntity getCreditCard(RequestMoviePaymentDTO requestMoviePaymentDTO, UserEntity userEntity) {
        for (int i = 0; i < userEntity.getCards().size(); i++) {
            if (Objects.equals(userEntity.getCards().get(i).getNumber(), requestMoviePaymentDTO.getCreditCardNumber())) {
                return userEntity.getCards().get(i);
            }
        }
        throw new CreditCardNotFoundException("credit card id: " + requestMoviePaymentDTO.getCreditCardNumber());
    }

    private RequestPaymentCustomerDTO buildCustomer(UserEntity userEntity) {
        return new RequestPaymentCustomerDTO(userEntity.getCpf());
    }
}
