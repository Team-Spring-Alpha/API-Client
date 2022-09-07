package br.com.compass.filmes.cliente.service;

import br.com.compass.filmes.cliente.dto.allocation.history.request.RequestAllocationDTO;
import br.com.compass.filmes.cliente.dto.allocation.history.request.RequestAllocationMovieDTO;
import br.com.compass.filmes.cliente.dto.movie.manager.RequestMoviePaymentDTO;
import br.com.compass.filmes.cliente.dto.payment.request.RequestPaymentDTO;
import br.com.compass.filmes.cliente.dto.payment.request.RequestPaymentCreditCardDTO;
import br.com.compass.filmes.cliente.dto.payment.request.RequestPaymentCustomerDTO;
import br.com.compass.filmes.cliente.dto.payment.response.*;
import br.com.compass.filmes.cliente.dto.movie.ResponseMovieByIdDTO;
import br.com.compass.filmes.cliente.entities.UserEntity;
import br.com.compass.filmes.cliente.entities.CreditCardEntity;
import br.com.compass.filmes.cliente.enums.PaymentVendorEnum;
import br.com.compass.filmes.cliente.enums.MovieLinks;
import br.com.compass.filmes.cliente.exceptions.BuyMovieNotFoundException;
import br.com.compass.filmes.cliente.exceptions.UserNotFoundException;
import br.com.compass.filmes.cliente.exceptions.CreditCardNotFoundException;
import br.com.compass.filmes.cliente.exceptions.RentMovieNotFoundException;
import br.com.compass.filmes.cliente.client.GatewayProxy;
import br.com.compass.filmes.cliente.client.MovieSearchProxy;
import br.com.compass.filmes.cliente.producer.MessageHistoryProducer;
import br.com.compass.filmes.cliente.repository.UserRepository;
import br.com.compass.filmes.cliente.util.EncriptPasswordUtil;
import br.com.compass.filmes.cliente.util.ValidateRequestMoviePaymentUtil;
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

    public ResponseGatewayReprovedDTO post(RequestMoviePaymentDTO requestMoviePaymentDTO) {
        validateRequestMoviePaymentUtil.validRequestMoviePayment(requestMoviePaymentDTO);
        UserEntity userEntity = userRepository.findById(requestMoviePaymentDTO.getUserId()).orElseThrow(() -> new UserNotFoundException("userId: "+ requestMoviePaymentDTO.getUserId()));
        CreditCardEntity creditCard = getCreditCard(requestMoviePaymentDTO, userEntity);

        List<ResponseMoviePaymentProcessDTO> moviePaymentProcessList = new ArrayList<>();
        Double amount = 0.0;

        if (requestMoviePaymentDTO.getMovies().getBuy() != null) {
            amount = processTheBuyMovieList(requestMoviePaymentDTO, moviePaymentProcessList, amount);
        }

        if (requestMoviePaymentDTO.getMovies().getRent() != null) {
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
        for (int i = 0; i < requestMoviePaymentDTO.getMovies().getRent().size(); i++) {
            Long movieId = requestMoviePaymentDTO.getMovies().getRent().get(i);
            ResponseMovieByIdDTO proxyMovieById = movieSearchProxy.getMovieById(requestMoviePaymentDTO.getMovies().getRent().get(i));
            String movieTitle = proxyMovieById.getMovieName();
            try {
                String movieStore = proxyMovieById.getJustWatch().getRent().get(0).getStore();
                Double rentPrice = proxyMovieById.getJustWatch().getRent().get(0).getPrice();
                amount += rentPrice;
                buildMoviesProcessList(movieId, movieTitle, movieStore, moviePaymentProcessList);

            } catch (NullPointerException exception) {
                throw new RentMovieNotFoundException("movie id: "+ movieId);
            }
        }
        return amount;
    }

    private Double processTheBuyMovieList(RequestMoviePaymentDTO requestMoviePaymentDTO, List<ResponseMoviePaymentProcessDTO> moviePaymentProcessList, Double amount) {
        for (int i = 0; i < requestMoviePaymentDTO.getMovies().getBuy().size(); i++) {
            Long movieId = requestMoviePaymentDTO.getMovies().getBuy().get(i);
            ResponseMovieByIdDTO proxyMovieById = movieSearchProxy.getMovieById(movieId);
            String movieTitle = proxyMovieById.getMovieName();
            try {
                String movieStore = proxyMovieById.getJustWatch().getBuy().get(0).getStore();
                Double buyPrice = proxyMovieById.getJustWatch().getBuy().get(0).getPrice();
                amount += buyPrice;
                buildMoviesProcessList(movieId, movieTitle, movieStore, moviePaymentProcessList);
            } catch (NullPointerException exception) {
                throw new BuyMovieNotFoundException("movie id: " + movieId);
            }
        }
        return amount;
    }

    private MovieLinks getMovieLinkFromlabel(String label) {
        return MovieLinks.valueOfLabel(label);
    }

    private void buildMoviesProcessList(Long movieId, String movieTitle, String movieStore, List<ResponseMoviePaymentProcessDTO> moviePaymentProcessList) {
        ResponseMoviePaymentProcessDTO moviePaymentProcess = new ResponseMoviePaymentProcessDTO();
        moviePaymentProcess.setMovieId(movieId);
        moviePaymentProcess.setTitle(movieTitle);
        MovieLinks movieLinks = getMovieLinkFromlabel(movieStore);
        moviePaymentProcess.setLink(movieLinks.getLink());
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
