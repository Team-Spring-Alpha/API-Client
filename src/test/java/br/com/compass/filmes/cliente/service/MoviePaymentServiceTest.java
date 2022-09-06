package br.com.compass.filmes.cliente.service;

import br.com.compass.filmes.cliente.builders.*;
import br.com.compass.filmes.cliente.dto.apiMovieManager.RequestMoviePayment;
import br.com.compass.filmes.cliente.dto.apiMovieManager.RequestRentOrBuy;
import br.com.compass.filmes.cliente.dto.apiPayment.response.ResponseAuth;
import br.com.compass.filmes.cliente.dto.apiPayment.response.ResponseGatewayReproved;
import br.com.compass.filmes.cliente.dto.apiPayment.response.ResponsePayment;
import br.com.compass.filmes.cliente.dto.apiPayment.response.ResponseProcessPayment;
import br.com.compass.filmes.cliente.dto.client.response.apiMovie.ResponseJustWatch;
import br.com.compass.filmes.cliente.dto.client.response.apiMovie.ResponseMovieById;
import br.com.compass.filmes.cliente.dto.client.response.apiMovie.ResponseRentAndBuy;
import br.com.compass.filmes.cliente.entities.ClientEntity;
import br.com.compass.filmes.cliente.entities.CreditCardEntity;
import br.com.compass.filmes.cliente.enums.MovieLinks;
import br.com.compass.filmes.cliente.exceptions.BuyMovieNotFoundException;
import br.com.compass.filmes.cliente.exceptions.ClientNotFoundException;
import br.com.compass.filmes.cliente.exceptions.CreditCardNotFoundException;
import br.com.compass.filmes.cliente.exceptions.RentMovieNotFoundException;
import br.com.compass.filmes.cliente.proxy.GatewayProxy;
import br.com.compass.filmes.cliente.proxy.MovieSearchProxy;
import br.com.compass.filmes.cliente.rabbitMq.MessageHistory;
import br.com.compass.filmes.cliente.repository.ClientRepository;
import br.com.compass.filmes.cliente.util.Md5;
import br.com.compass.filmes.cliente.util.ValidRequestMoviePayment;
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
    private ClientRepository clientRepository;

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
        ClientEntity clientEntity = ClientEntityBuilder.one().withId("1L").now();
        RequestMoviePayment requestMoviePayment = new RequestMoviePayment();
        requestMoviePayment.setUserId("2L");

        Assertions.assertThrows(ClientNotFoundException.class, () -> moviePaymentService.post(requestMoviePayment));
    }

    @Test
    @DisplayName("should throw credit card not found when not found a credt card from that client")
    void shoudThrowCreditCardNotFoundWhenNotFoundACreditCard() {
        RequestMoviePayment requestMoviePayment = new RequestMoviePayment();
        requestMoviePayment.setCreditCardNumber("not found");
        ClientEntity clientEntity = ClientEntityBuilder.one().now();

        Mockito.when(clientRepository.findById(any())).thenReturn(Optional.ofNullable(clientEntity));

        Assertions.assertThrows(CreditCardNotFoundException.class, () -> moviePaymentService.post(requestMoviePayment));
    }


    @Test
    @DisplayName("should throw buy movie not found when external api dont return where to buy that movie")
    void shouldThrowBuyMovieNotFoundExceptionWhenExternalApiReturnNullFromBuyProviderList() {
        RequestRentOrBuy rentOrBuy = RequestRentOrBuyBuilder.one().withRentList(null).now();

        RequestMoviePayment moviePayment = RequestMoviePaymentBuilder.one()
                .withCreditCardNumber("test")
                .withRentOrBuy(rentOrBuy)
                .now();

        ClientEntity clientEntity = buildClientEntityWithCreditCardNumber("test");

        ResponseMovieById responseMovieById = buildResponseMovieById();
        responseMovieById.getJustWatch().setBuy(null);

        Mockito.when(clientRepository.findById(any())).thenReturn(Optional.of(clientEntity));
        Mockito.when(movieSearchProxy.getMovieById(any())).thenReturn(responseMovieById);

        Assertions.assertThrows(BuyMovieNotFoundException.class, () -> moviePaymentService.post(moviePayment));
    }

    @Test
    @DisplayName("should throw rent movie not found when external api dont return where to rent that movie")
    void shouldThrowBuyMovieNotFoundExceptionWhenExternalApiReturnNullFromRentProviderList() {
        RequestRentOrBuy rentOrBuy = RequestRentOrBuyBuilder.one().withBuyList(null).now();
        RequestMoviePayment moviePayment = RequestMoviePaymentBuilder.one()
                .withCreditCardNumber("test")
                .withRentOrBuy(rentOrBuy)
                .now();

        ClientEntity clientEntity = buildClientEntityWithCreditCardNumber("test");

        ResponseMovieById responseMovieById = buildResponseMovieById();
        responseMovieById.getJustWatch().setRent(null);

        Mockito.when(clientRepository.findById(any())).thenReturn(Optional.of(clientEntity));
        Mockito.when(movieSearchProxy.getMovieById(any())).thenReturn(responseMovieById);

        Assertions.assertThrows(RentMovieNotFoundException.class, () -> moviePaymentService.post(moviePayment));
    }

    @Test
    @DisplayName("should process with sucessful a payment request reproved by gateway")
    void shouldProcessWithSucessfulAPaymentReprovedByGateway() {
        RequestMoviePayment moviePayment = RequestMoviePaymentBuilder.one().withCreditCardNumber("test").now();
        ClientEntity clientEntity = buildClientEntityWithCreditCardNumber("test");

        ResponseMovieById responseMovieById = buildResponseMovieById();

        ResponseAuth responseAuth = ResponseAuthBuilder.one().now();
        ResponsePayment responsePayment = buildResponsePaymentFailed();

        Mockito.when(clientRepository.findById(any())).thenReturn(Optional.of(clientEntity));
        Mockito.when(movieSearchProxy.getMovieById(any())).thenReturn(responseMovieById);
        Mockito.when(gatewayProxy.getAuthToken(any())).thenReturn(responseAuth);
        Mockito.when(gatewayProxy.getPayment(any(), any())).thenReturn(responsePayment);

        ResponseGatewayReproved responseGatewayReproved = moviePaymentService.post(moviePayment);

        Assertions.assertEquals(responsePayment.getStatus(), responseGatewayReproved.getPaymentStatus());
        Assertions.assertEquals(responsePayment.getAuthorization().getReasonMessage(), responseGatewayReproved.getCause());
    }

    @Test
    @DisplayName("should process with sucessful a payment request approved by gateway")
    void shouldProcessWithSucessfulAPaymentApprovedByGateway() {
        RequestMoviePayment moviePayment = RequestMoviePaymentBuilder.one().withCreditCardNumber("0081").now();
        ClientEntity clientEntity = buildClientEntityWithCreditCardNumber("0081");

        ResponseMovieById responseMovieById = buildResponseMovieById();

        ResponseAuth responseAuth = ResponseAuthBuilder.one().now();
        ResponsePayment responsePayment = buildResponsePaymentApproved();

        Mockito.when(clientRepository.findById(any())).thenReturn(Optional.of(clientEntity));
        Mockito.when(movieSearchProxy.getMovieById(any())).thenReturn(responseMovieById);
        Mockito.when(gatewayProxy.getAuthToken(any())).thenReturn(responseAuth);
        Mockito.when(gatewayProxy.getPayment(any(), any())).thenReturn(responsePayment);

        ResponseGatewayReproved responseGatewayApproved = moviePaymentService.post(moviePayment);
        Assertions.assertEquals(responsePayment.getStatus(), responseGatewayApproved.getPaymentStatus());
        Assertions.assertEquals(responsePayment.getAuthorization().getReasonMessage(), responseGatewayApproved.getCause());

    }



    private ClientEntity buildClientEntityWithCreditCardNumber(String creditCardNumber) {
        CreditCardEntity creditCard = CreditCardEntityBuilder.one().withCreditCardNumber(creditCardNumber).now();
        List<CreditCardEntity> creditCardEntityList = new ArrayList<>();
        creditCardEntityList.add(creditCard);
        return ClientEntityBuilder.one().withCreditCard(creditCardEntityList).now();
    }

    private ResponsePayment buildResponsePaymentFailed() {
        ResponseProcessPayment responseProcessPayment = ResponseProcessPayment.builder()
                .reasonMessage("test fail")
                .build();

        return ResponsePayment.builder()
                .status("REPROVED")
                .authorization(responseProcessPayment)
                .build();
    }

    private ResponsePayment buildResponsePaymentApproved() {
        ResponseProcessPayment responseProcessPayment = ResponseProcessPayment.builder()
                .reasonMessage("approved")
                .build();

        return ResponsePayment.builder()
                .status("APPROVED")
                .authorization(responseProcessPayment)
                .build();
    }


    private ResponseMovieById buildResponseMovieById() {
        ResponseRentAndBuy responseRentAndBuy = ResponseRentAndBuy.builder()
                .price(50.0)
                .store(MovieLinks.NETFLIX.getLabel())
                .build();
        List<ResponseRentAndBuy> responseRentAndBuyList = new ArrayList<>();
        responseRentAndBuyList.add(responseRentAndBuy);

        ResponseJustWatch responseJustWatch = ResponseJustWatch.builder()
                .rent(responseRentAndBuyList)
                .buy(responseRentAndBuyList)
                .build();

        ResponseMovieById responseMovieById = ResponseMovieById.builder()
                .id(1L)
                .movieName("test")
                .justWatch(responseJustWatch)
                .build();

        return responseMovieById;
    }



}