package br.com.compass.filmes.cliente.service;

import br.com.compass.filmes.cliente.builders.*;
import br.com.compass.filmes.cliente.dto.client.request.RequestClient;
import br.com.compass.filmes.cliente.dto.client.request.RequestClientUpdate;
import br.com.compass.filmes.cliente.dto.client.request.RequestCreditCard;
import br.com.compass.filmes.cliente.dto.client.request.RequestSetStatusClientAccount;
import br.com.compass.filmes.cliente.dto.client.response.ResponseClient;
import br.com.compass.filmes.cliente.entities.ClientEntity;
import br.com.compass.filmes.cliente.repository.ClientRepository;
import br.com.compass.filmes.cliente.util.Md5;
import br.com.compass.filmes.cliente.util.ValidRequestClient;
import br.com.compass.filmes.cliente.util.ValidRequestCreditCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = ClientService.class)
class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private Md5 md5;

    @SpyBean
    private ModelMapper modelMapper;

    @SpyBean
    private ValidRequestCreditCard validRequestCreditCard;

    @SpyBean
    private ValidRequestClient validRequestClient;

    @Test
    @DisplayName("should successful create a client")
    void shouldSuccesfulCreateAClient() {
        RequestClient requestClient = RequestClientBuilder.one().now();
        ClientEntity clientEntity = ClientEntityBuilder.one().withRequestClient(requestClient).now();

        Mockito.when(clientRepository.save(any())).thenReturn(clientEntity);

        ResponseClient responseClient = clientService.post(requestClient);

        Assertions.assertEquals(clientEntity.getClientName(), responseClient.getClientName());
        Assertions.assertEquals(clientEntity.getClientBirthDate(), responseClient.getClientBirthDate());
        Assertions.assertEquals(clientEntity.getCreditCards().get(0).getClientCreditCardBrand(), responseClient.getCreditCards().get(0).getClientCreditCardBrand());
        Assertions.assertEquals(clientEntity.getCreditCards().get(1).getClientCreditCardBrand(), responseClient.getCreditCards().get(1).getClientCreditCardBrand());
    }

    @Test
    @DisplayName("should throw exception when create a client with a wrong category")
    void shouldThrowExceptionWhenCreateAClientWithWrongCategory() {
        List<String> categoriesList = new ArrayList<>();
        categoriesList.add("test");

        RequestClient requestClient = RequestClientBuilder.one()
                .withClientCategory(categoriesList)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> clientService.post(requestClient));
    }

    @Test
    @DisplayName("should throw exception when create a client with a wrong credit card brand")
    void shouldThrowExceptionWhenCreateAClientWithWrongCreditCardBrand() {
        List<RequestCreditCard> requestCreditCard = RequestCreditCardBuilder.one()
                .withClientCreditCardBrand("test")
                .list();

        RequestClient requestClient = RequestClientBuilder.one()
                .withCreditCards(requestCreditCard)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> clientService.post(requestClient));
    }

    @Test
    @DisplayName("should throw exception when create a client with a wrong credit card security code")
    void shouldThrowExceptionWhenCreateAClientWithWrongCreditCardSecurityCode() {
        List<RequestCreditCard> requestCreditCard = RequestCreditCardBuilder.one()
                .withClientCreditCardSecurityCode("01x")
                .list();

        RequestClient requestClient = RequestClientBuilder.one()
                .withCreditCards(requestCreditCard)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> clientService.post(requestClient));
    }

    @Test
    @DisplayName("should throw exception when create a client with a wrong credit card security code (missing 1 digit)")
    void shouldThrowExceptionWhenCreateAClientWithCreditCardSecurityCodeMissingOneDigit() {
        List<RequestCreditCard> requestCreditCard = RequestCreditCardBuilder.one()
                .withClientCreditCardSecurityCode("01")
                .list();

        RequestClient requestClient = RequestClientBuilder.one()
                .withCreditCards(requestCreditCard)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> clientService.post(requestClient));
    }

    @Test
    @DisplayName("should throw exception when create a client with a wrong credit card month")
    void shouldThrowExceptionWhenCreateAClientWithWrongCreditCardMonth() {
        List<RequestCreditCard> requestCreditCard = RequestCreditCardBuilder.one()
                .withClientCreditCardMonthExpiration("01")
                .list();

        RequestClient requestClient = RequestClientBuilder.one()
                .withCreditCards(requestCreditCard)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> clientService.post(requestClient));
        requestClient.getCreditCards().get(0).setClientCreditCardMonthExpiration("teste");
        Assertions.assertThrows(ResponseStatusException.class, () -> clientService.post(requestClient));
        requestClient.getCreditCards().get(0).setClientCreditCardMonthExpiration("13");
        Assertions.assertThrows(ResponseStatusException.class, () -> clientService.post(requestClient));
        requestClient.getCreditCards().get(0).setClientCreditCardMonthExpiration("-3");
        Assertions.assertThrows(ResponseStatusException.class, () -> clientService.post(requestClient));
    }

    @Test
    @DisplayName("should throw exception when create a client with a wrong credit card year (+6 year)")
    void shouldThrowExceptionWhenCreateClientWithWrongCreditCardYearAfterSixYears() {
        String yearString = LocalDate.now().plusYears(6).toString();
        List<RequestCreditCard> requestCreditCard = RequestCreditCardBuilder.one()
                .withClientCreditCardYearExpiration(yearString)
                .list();

        RequestClient requestClient = RequestClientBuilder.one()
                .withCreditCards(requestCreditCard)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> clientService.post(requestClient));
    }

    @Test
    @DisplayName("should throw exception when create a client with a wrong credit card year (-1 year)")
    void shouldThrowExceptionWhenCreateClientWithCreditCardYearBeforeNow() {
        String yearString = LocalDate.now().minusYears(1).toString();
        List<RequestCreditCard> requestCreditCard = RequestCreditCardBuilder.one()
                .withClientCreditCardYearExpiration(yearString)
                .list();

        RequestClient requestClient = RequestClientBuilder.one()
                .withCreditCards(requestCreditCard)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> clientService.post(requestClient));
    }

    @Test
    @DisplayName("should throw exception when create a client with a wrong credit card year")
    void shouldThrowExceptionWhenCreateClientWithCreditCardYear() {
        String yearString = "teste";
        List<RequestCreditCard> requestCreditCard = RequestCreditCardBuilder.one()
                .withClientCreditCardYearExpiration(yearString)
                .list();

        RequestClient requestClient = RequestClientBuilder.one()
                .withCreditCards(requestCreditCard)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> clientService.post(requestClient));
    }

    @Test
    @DisplayName("should set the client's status account as blocked")
    void shouldSetTheClientStatusAccountAsBlocked(){
        RequestClient requestClient = RequestClientBuilder.one().now();
        ClientEntity clientEntity = ClientEntityBuilder.one().withRequestClient(requestClient).now();
        RequestSetStatusClientAccount requestSetStatusClientAccount =
                RequestSetStatusClientAccountBuilder.blocked().now();

        Mockito.when(clientRepository.findById(any())).thenReturn(Optional.of(clientEntity));

        ResponseClient responseClient = clientService.setStatusClientAccount("1",
                requestSetStatusClientAccount);

        Mockito.verify(clientRepository).save(clientEntity);
        Assertions.assertTrue(responseClient.isClientIsBlocked());
    }
    @Test
    @DisplayName("should set the client's status account as unlocked")
    void shouldSetTheClientStatusAccountAsUnlocked(){
        RequestClient requestClient = RequestClientBuilder.one().now();
        ClientEntity clientEntity = ClientEntityBuilder.one().withRequestClient(requestClient).now();
        RequestSetStatusClientAccount requestSetStatusClientAccount =
                RequestSetStatusClientAccountBuilder.unlocked().now();

        Mockito.when(clientRepository.findById(any())).thenReturn(Optional.of(clientEntity));

        ResponseClient responseClient = clientService.setStatusClientAccount("1",
                requestSetStatusClientAccount);

        Mockito.verify(clientRepository).save(clientEntity);
        Assertions.assertFalse(responseClient.isClientIsBlocked());
    }
    @Test
    @DisplayName("should throw an exception when the setStatusClientAccount method receives a nonexistent id")
    void shouldThrowAnExceptionWhenTheSetStatusClientAccountMethodReceivesANonexistentId(){
        RequestClient requestClient = RequestClientBuilder.one().now();
        ClientEntity clientEntity = ClientEntityBuilder.one().withRequestClient(requestClient).now();
        RequestSetStatusClientAccount requestSetStatusClientAccount =
                RequestSetStatusClientAccountBuilder.blocked().now();

        Assertions.assertThrows(ResponseStatusException.class, () -> clientService
                .setStatusClientAccount("2", requestSetStatusClientAccount));
    }

    @Test
    @DisplayName("should successful update a client")
    void shouldSuccessfulUpdateAClient(){
        RequestClient requestClient = RequestClientBuilder.one().now();
        ClientEntity clientEntity = ClientEntityBuilder.one().withRequestClient(requestClient).now();
        RequestClientUpdate clientUpdate = RequestClientUpdateBuilder.one().now();

        Mockito.when(clientRepository.findById(any())).thenReturn(Optional.of(clientEntity));
        Mockito.when(clientRepository.save(any())).thenReturn(clientEntity);

        ResponseClient responseClient = clientService.patch("idTeste", clientUpdate);

        Assertions.assertEquals(clientEntity.getClientEmail(), clientUpdate.getClientEmail());
        Assertions.assertEquals(clientEntity.getClientPassword(), clientUpdate.getClientPassword());
        Assertions.assertEquals(clientEntity.getClientName(), responseClient.getClientName());
        Assertions.assertEquals(clientEntity.getClientBirthDate(), responseClient.getClientBirthDate());
    }

    @Test
    @DisplayName("should throw an exception when the patch method receives a nonexistent id")
    void shouldThrowAnExceptionWhenThePatchMethodReceivesANonexistentId(){
        RequestClient requestClient = RequestClientBuilder.one().now();
        ClientEntity clientEntity = ClientEntityBuilder.one().withRequestClient(requestClient).now();
        RequestClientUpdate clientUpdate = RequestClientUpdateBuilder.one().now();

        Assertions.assertThrows(ResponseStatusException.class, () -> clientService
                .patch("2", clientUpdate));
    }

    @Test
    @DisplayName("Should bring all client")
    public void shouldFindAllClient(){
        Mockito.when(clientRepository.findAll()).thenReturn(Arrays.asList(ClientEntityBuilder.one().now()));
        List<ResponseClient> listClient = clientService.returnAllClients();
        Assertions.assertNotNull(listClient);
        Assertions.assertEquals(1, listClient.size());
        Assertions.assertEquals("Jetosvaldo", listClient.get(0).getClientName());
    }

    @Test
    @DisplayName("Should bring a client by id")
    public void shouldFindClientById(){
        RequestClient requestClient = RequestClientBuilder.one().now();
        ClientEntity clientEntity = ClientEntityBuilder.one().withRequestClient(requestClient).now();
        Mockito.when(clientRepository.findById(clientEntity.getId())).thenReturn(Optional.of(clientEntity));
        clientService.returnClientById(clientEntity.getId());
        Mockito.verify(clientRepository, Mockito.times(1)).findById(clientEntity.getId());
        Assertions.assertEquals("Jetosvaldo", clientEntity.getClientName());
    }

    @Test
    @DisplayName("Shouldn't find a client by id and should throw an exception")
    public void shouldNotFindClientById(){
        Assertions.assertThrows(ResponseStatusException.class, () -> clientService
                .returnClientById("2"));
    }
}
