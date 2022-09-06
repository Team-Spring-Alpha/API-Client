package br.com.compass.filmes.user.service;

import br.com.compass.filmes.user.builders.*;
import br.com.compass.filmes.user.dto.movie.manager.RequestMoviePaymentDTO;
import br.com.compass.filmes.user.dto.movie.manager.RequestRentOrBuyDTO;
import br.com.compass.filmes.user.dto.payment.response.ResponseAuthDTO;
import br.com.compass.filmes.user.dto.payment.response.ResponseGatewayReprovedDTO;
import br.com.compass.filmes.user.dto.payment.response.ResponsePaymentDTO;
import br.com.compass.filmes.user.dto.payment.response.ResponseProcessPaymentDTO;
import br.com.compass.filmes.user.dto.user.response.apiMovie.ResponseJustWatchDTO;
import br.com.compass.filmes.user.dto.user.response.apiMovie.ResponseMovieByIdDTO;
import br.com.compass.filmes.user.dto.user.response.apiMovie.ResponseRentAndBuyDTO;
import br.com.compass.filmes.user.entities.UserEntity;
import br.com.compass.filmes.user.entities.CreditCardEntity;
import br.com.compass.filmes.user.enums.MovieLinks;
import br.com.compass.filmes.user.exceptions.BuyMovieNotFoundException;
import br.com.compass.filmes.user.exceptions.UserNotFoundException;
import br.com.compass.filmes.user.exceptions.CreditCardNotFoundException;
import br.com.compass.filmes.user.exceptions.RentMovieNotFoundException;
import br.com.compass.filmes.user.client.GatewayProxy;
import br.com.compass.filmes.user.client.MovieSearchProxy;
import br.com.compass.filmes.user.rabbitMq.MessageHistory;
import br.com.compass.filmes.user.repository.UserRepository;
import br.com.compass.filmes.user.util.Md5;
import br.com.compass.filmes.user.util.ValidRequestMoviePayment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = MoviePaymentService.class)
class MoviePaymentServiceTest {

    @Autowired
    private MoviePaymentService moviePaymentService;

    @SpyBean
    private ModelMapper modelMapper;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private MovieSearchProxy movieSearchProxy;

    @MockBean
    private  GatewayProxy gatewayProxy;

    @MockBean
    private  MessageHistory messageHistory;

    @MockBean
    private  Md5 md5;

    @MockBean
    private ValidRequestMoviePayment validRequestMoviePayment;

    @Test
    @DisplayName("should throw client not found exception when not found a client by id")
    void shoudThrowClientNotFoundWhenNotFoundAClient() {
        UserEntity userEntity = UserEntityBuilder.one().withId("1L").now();
        RequestMoviePaymentDTO requestMoviePaymentDTO = new RequestMoviePaymentDTO();
        requestMoviePaymentDTO.setUserId("2L");

        Assertions.assertThrows(UserNotFoundException.class, () -> moviePaymentService.post(requestMoviePaymentDTO));
    }

    @Test
    @DisplayName("should throw credit card not found when not found a credt card from that client")
    void shoudThrowCreditCardNotFoundWhenNotFoundACreditCard() {
        RequestMoviePaymentDTO requestMoviePaymentDTO = new RequestMoviePaymentDTO();
        requestMoviePaymentDTO.setCreditCardNumber("not found");
        UserEntity userEntity = UserEntityBuilder.one().now();

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.ofNullable(userEntity));

        Assertions.assertThrows(CreditCardNotFoundException.class, () -> moviePaymentService.post(requestMoviePaymentDTO));
    }


    @Test
    @DisplayName("should throw buy movie not found when external api dont return where to buy that movie")
    void shouldThrowBuyMovieNotFoundExceptionWhenExternalApiReturnNullFromBuyProviderList() {
        RequestRentOrBuyDTO rentOrBuy = RequestRentOrBuyBuilder.one().withRentList(null).now();

        RequestMoviePaymentDTO moviePayment = RequestMoviePaymentBuilder.one()
                .withCreditCardNumber("test")
                .withRentOrBuy(rentOrBuy)
                .now();

        UserEntity userEntity = buildClientEntityWithCreditCardNumber("test");

        ResponseMovieByIdDTO responseMovieByIdDTO = buildResponseMovieById();
        responseMovieByIdDTO.getJustWatch().setBuy(null);

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));
        Mockito.when(movieSearchProxy.getMovieById(any())).thenReturn(responseMovieByIdDTO);

        Assertions.assertThrows(BuyMovieNotFoundException.class, () -> moviePaymentService.post(moviePayment));
    }

    @Test
    @DisplayName("should throw rent movie not found when external api dont return where to rent that movie")
    void shouldThrowBuyMovieNotFoundExceptionWhenExternalApiReturnNullFromRentProviderList() {
        RequestRentOrBuyDTO rentOrBuy = RequestRentOrBuyBuilder.one().withBuyList(null).now();
        RequestMoviePaymentDTO moviePayment = RequestMoviePaymentBuilder.one()
                .withCreditCardNumber("test")
                .withRentOrBuy(rentOrBuy)
                .now();

        UserEntity userEntity = buildClientEntityWithCreditCardNumber("test");

        ResponseMovieByIdDTO responseMovieByIdDTO = buildResponseMovieById();
        responseMovieByIdDTO.getJustWatch().setRent(null);

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));
        Mockito.when(movieSearchProxy.getMovieById(any())).thenReturn(responseMovieByIdDTO);

        Assertions.assertThrows(RentMovieNotFoundException.class, () -> moviePaymentService.post(moviePayment));
    }

    @Test
    @DisplayName("should process with sucessful a payment request reproved by gateway")
    void shouldProcessWithSucessfulAPaymentReprovedByGateway() {
        RequestMoviePaymentDTO moviePayment = RequestMoviePaymentBuilder.one().withCreditCardNumber("test").now();
        UserEntity userEntity = buildClientEntityWithCreditCardNumber("test");

        ResponseMovieByIdDTO responseMovieByIdDTO = buildResponseMovieById();

        ResponseAuthDTO responseAuthDTO = ResponseAuthBuilder.one().now();
        ResponsePaymentDTO responsePaymentDTO = buildResponsePaymentFailed();

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));
        Mockito.when(movieSearchProxy.getMovieById(any())).thenReturn(responseMovieByIdDTO);
        Mockito.when(gatewayProxy.getAuthToken(any())).thenReturn(responseAuthDTO);
        Mockito.when(gatewayProxy.getPayment(any(), any())).thenReturn(responsePaymentDTO);

        ResponseGatewayReprovedDTO responseGatewayReprovedDTO = moviePaymentService.post(moviePayment);

        Assertions.assertEquals(responsePaymentDTO.getStatus(), responseGatewayReprovedDTO.getPaymentStatus());
        Assertions.assertEquals(responsePaymentDTO.getAuthorization().getReasonMessage(), responseGatewayReprovedDTO.getCause());
    }

    @Test
    @DisplayName("should process with sucessful a payment request approved by gateway")
    void shouldProcessWithSucessfulAPaymentApprovedByGateway() {
        RequestMoviePaymentDTO moviePayment = RequestMoviePaymentBuilder.one().withCreditCardNumber("0081").now();
        UserEntity userEntity = buildClientEntityWithCreditCardNumber("0081");

        ResponseMovieByIdDTO responseMovieByIdDTO = buildResponseMovieById();

        ResponseAuthDTO responseAuthDTO = ResponseAuthBuilder.one().now();
        ResponsePaymentDTO responsePaymentDTO = buildResponsePaymentApproved();

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));
        Mockito.when(movieSearchProxy.getMovieById(any())).thenReturn(responseMovieByIdDTO);
        Mockito.when(gatewayProxy.getAuthToken(any())).thenReturn(responseAuthDTO);
        Mockito.when(gatewayProxy.getPayment(any(), any())).thenReturn(responsePaymentDTO);

        ResponseGatewayReprovedDTO responseGatewayApproved = moviePaymentService.post(moviePayment);
        Assertions.assertEquals(responsePaymentDTO.getStatus(), responseGatewayApproved.getPaymentStatus());
        Assertions.assertEquals(responsePaymentDTO.getAuthorization().getReasonMessage(), responseGatewayApproved.getCause());

    }



    private UserEntity buildClientEntityWithCreditCardNumber(String creditCardNumber) {
        CreditCardEntity creditCard = CreditCardEntityBuilder.one().withCreditCardNumber(creditCardNumber).now();
        List<CreditCardEntity> creditCardEntityList = new ArrayList<>();
        creditCardEntityList.add(creditCard);
        return UserEntityBuilder.one().withCreditCard(creditCardEntityList).now();
    }

    private ResponsePaymentDTO buildResponsePaymentFailed() {
        ResponseProcessPaymentDTO responseProcessPaymentDTO = ResponseProcessPaymentDTO.builder()
                .reasonMessage("test fail")
                .build();

        return ResponsePaymentDTO.builder()
                .status("REPROVED")
                .authorization(responseProcessPaymentDTO)
                .build();
    }

    private ResponsePaymentDTO buildResponsePaymentApproved() {
        ResponseProcessPaymentDTO responseProcessPaymentDTO = ResponseProcessPaymentDTO.builder()
                .reasonMessage("approved")
                .build();

        return ResponsePaymentDTO.builder()
                .status("APPROVED")
                .authorization(responseProcessPaymentDTO)
                .build();
    }


    private ResponseMovieByIdDTO buildResponseMovieById() {
        ResponseRentAndBuyDTO responseRentAndBuyDTO = ResponseRentAndBuyDTO.builder()
                .price(50.0)
                .store(MovieLinks.NETFLIX.getLabel())
                .build();
        List<ResponseRentAndBuyDTO> responseRentAndBuyDTOList = new ArrayList<>();
        responseRentAndBuyDTOList.add(responseRentAndBuyDTO);

        ResponseJustWatchDTO responseJustWatchDTO = ResponseJustWatchDTO.builder()
                .rent(responseRentAndBuyDTOList)
                .buy(responseRentAndBuyDTOList)
                .build();

        ResponseMovieByIdDTO responseMovieByIdDTO = ResponseMovieByIdDTO.builder()
                .id(1L)
                .movieName("test")
                .justWatch(responseJustWatchDTO)
                .build();

        return responseMovieByIdDTO;
    }



}