package br.com.compass.filmes.cliente.service;

import br.com.compass.filmes.cliente.builders.*;
import br.com.compass.filmes.cliente.dto.user.request.RequestUser;
import br.com.compass.filmes.cliente.dto.user.request.RequestUserUpdate;
import br.com.compass.filmes.cliente.dto.user.request.RequestCreditCard;
import br.com.compass.filmes.cliente.dto.user.request.RequestSetStatusUserAccount;
import br.com.compass.filmes.cliente.dto.user.response.ResponseUser;
import br.com.compass.filmes.cliente.entities.UserEntity;
import br.com.compass.filmes.cliente.repository.UserRepository;
import br.com.compass.filmes.cliente.util.Md5;
import br.com.compass.filmes.cliente.util.ValidRequestUser;
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

@SpringBootTest(classes = UserService.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private Md5 md5;

    @SpyBean
    private ModelMapper modelMapper;

    @SpyBean
    private ValidRequestCreditCard validRequestCreditCard;

    @SpyBean
    private ValidRequestUser validRequestUser;

    @Test
    @DisplayName("should successful create a client")
    void shouldSuccesfulCreateAClient() {
        RequestUser requestUser = RequestUserBuilder.one().now();
        UserEntity userEntity = UserEntityBuilder.one().withRequestClient(requestUser).now();

        Mockito.when(userRepository.save(any())).thenReturn(userEntity);

        ResponseUser responseUser = userService.post(requestUser);

        Assertions.assertEquals(userEntity.getName(), responseUser.getName());
        Assertions.assertEquals(userEntity.getBirthDate(), responseUser.getBirthDate());
        Assertions.assertEquals(userEntity.getCards().get(0).getBrand(), responseUser.getCards().get(0).getBrand());
        Assertions.assertEquals(userEntity.getCards().get(1).getBrand(), responseUser.getCards().get(1).getBrand());
    }

    @Test
    @DisplayName("should throw exception when create a client with a wrong category")
    void shouldThrowExceptionWhenCreateAClientWithWrongCategory() {
        List<String> categoriesList = new ArrayList<>();
        categoriesList.add("test");

        RequestUser requestUser = RequestUserBuilder.one()
                .withClientCategory(categoriesList)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> userService.post(requestUser));
    }

    @Test
    @DisplayName("should throw exception when create a client with a wrong credit card brand")
    void shouldThrowExceptionWhenCreateAClientWithWrongCreditCardBrand() {
        List<RequestCreditCard> requestCreditCard = RequestCreditCardBuilder.one()
                .withClientCreditCardBrand("test")
                .list();

        RequestUser requestUser = RequestUserBuilder.one()
                .withCreditCards(requestCreditCard)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> userService.post(requestUser));
    }

    @Test
    @DisplayName("should throw exception when create a client with a wrong credit card security code")
    void shouldThrowExceptionWhenCreateAClientWithWrongCreditCardSecurityCode() {
        List<RequestCreditCard> requestCreditCard = RequestCreditCardBuilder.one()
                .withClientCreditCardSecurityCode("01x")
                .list();

        RequestUser requestUser = RequestUserBuilder.one()
                .withCreditCards(requestCreditCard)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> userService.post(requestUser));
    }

    @Test
    @DisplayName("should throw exception when create a client with a wrong credit card security code (missing 1 digit)")
    void shouldThrowExceptionWhenCreateAClientWithCreditCardSecurityCodeMissingOneDigit() {
        List<RequestCreditCard> requestCreditCard = RequestCreditCardBuilder.one()
                .withClientCreditCardSecurityCode("01")
                .list();

        RequestUser requestUser = RequestUserBuilder.one()
                .withCreditCards(requestCreditCard)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> userService.post(requestUser));
    }

    @Test
    @DisplayName("should throw exception when create a client with a wrong credit card month")
    void shouldThrowExceptionWhenCreateAClientWithWrongCreditCardMonth() {
        List<RequestCreditCard> requestCreditCard = RequestCreditCardBuilder.one()
                .withClientCreditCardMonthExpiration("01")
                .list();

        RequestUser requestUser = RequestUserBuilder.one()
                .withCreditCards(requestCreditCard)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> userService.post(requestUser));
        requestUser.getCards().get(0).setMonthExpiration("teste");
        Assertions.assertThrows(ResponseStatusException.class, () -> userService.post(requestUser));
        requestUser.getCards().get(0).setMonthExpiration("13");
        Assertions.assertThrows(ResponseStatusException.class, () -> userService.post(requestUser));
        requestUser.getCards().get(0).setMonthExpiration("-3");
        Assertions.assertThrows(ResponseStatusException.class, () -> userService.post(requestUser));
    }

    @Test
    @DisplayName("should throw exception when create a client with a wrong credit card year (+6 year)")
    void shouldThrowExceptionWhenCreateClientWithWrongCreditCardYearAfterSixYears() {
        String yearString = LocalDate.now().plusYears(6).toString();
        List<RequestCreditCard> requestCreditCard = RequestCreditCardBuilder.one()
                .withClientCreditCardYearExpiration(yearString)
                .list();

        RequestUser requestUser = RequestUserBuilder.one()
                .withCreditCards(requestCreditCard)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> userService.post(requestUser));
    }

    @Test
    @DisplayName("should throw exception when create a client with a wrong credit card year (-1 year)")
    void shouldThrowExceptionWhenCreateClientWithCreditCardYearBeforeNow() {
        String yearString = LocalDate.now().minusYears(1).toString();
        List<RequestCreditCard> requestCreditCard = RequestCreditCardBuilder.one()
                .withClientCreditCardYearExpiration(yearString)
                .list();

        RequestUser requestUser = RequestUserBuilder.one()
                .withCreditCards(requestCreditCard)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> userService.post(requestUser));
    }

    @Test
    @DisplayName("should throw exception when create a client with a wrong credit card year")
    void shouldThrowExceptionWhenCreateClientWithCreditCardYear() {
        String yearString = "teste";
        List<RequestCreditCard> requestCreditCard = RequestCreditCardBuilder.one()
                .withClientCreditCardYearExpiration(yearString)
                .list();

        RequestUser requestUser = RequestUserBuilder.one()
                .withCreditCards(requestCreditCard)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> userService.post(requestUser));
    }

    @Test
    @DisplayName("should set the client's status account as blocked")
    void shouldSetTheClientStatusAccountAsBlocked(){
        RequestUser requestUser = RequestUserBuilder.one().now();
        UserEntity userEntity = UserEntityBuilder.one().withRequestClient(requestUser).now();
        RequestSetStatusUserAccount requestSetStatusUserAccount =
                RequestSetStatusUserAccountBuilder.blocked().now();

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));

        ResponseUser responseUser = userService.setStatusUserAccount("1",
                requestSetStatusUserAccount);

        Mockito.verify(userRepository).save(userEntity);
        Assertions.assertTrue(responseUser.isBlocked());
    }
    @Test
    @DisplayName("should set the client's status account as unlocked")
    void shouldSetTheClientStatusAccountAsUnlocked(){
        RequestUser requestUser = RequestUserBuilder.one().now();
        UserEntity userEntity = UserEntityBuilder.one().withRequestClient(requestUser).now();
        RequestSetStatusUserAccount requestSetStatusUserAccount =
                RequestSetStatusUserAccountBuilder.unlocked().now();

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));

        ResponseUser responseUser = userService.setStatusUserAccount("1",
                requestSetStatusUserAccount);

        Mockito.verify(userRepository).save(userEntity);
        Assertions.assertFalse(responseUser.isBlocked());
    }
    @Test
    @DisplayName("should throw an exception when the setStatusClientAccount method receives a nonexistent id")
    void shouldThrowAnExceptionWhenTheSetStatusClientAccountMethodReceivesANonexistentId(){
        RequestUser requestUser = RequestUserBuilder.one().now();
        UserEntity userEntity = UserEntityBuilder.one().withRequestClient(requestUser).now();
        RequestSetStatusUserAccount requestSetStatusUserAccount =
                RequestSetStatusUserAccountBuilder.blocked().now();

        Assertions.assertThrows(ResponseStatusException.class, () -> userService
                .setStatusUserAccount("2", requestSetStatusUserAccount));
    }

    @Test
    @DisplayName("should successful update a client")
    void shouldSuccessfulUpdateAClient(){
        RequestUser requestUser = RequestUserBuilder.one().now();
        UserEntity userEntity = UserEntityBuilder.one().withRequestClient(requestUser).now();
        RequestUserUpdate clientUpdate = RequestClientUpdateBuilder.one().now();

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));
        Mockito.when(userRepository.save(any())).thenReturn(userEntity);

        ResponseUser responseUser = userService.patch("idTeste", clientUpdate);

        Assertions.assertEquals(userEntity.getEmail(), clientUpdate.getEmail());
        Assertions.assertEquals(userEntity.getPassword(), clientUpdate.getPassword());
        Assertions.assertEquals(userEntity.getName(), responseUser.getName());
        Assertions.assertEquals(userEntity.getBirthDate(), responseUser.getBirthDate());
    }

    @Test
    @DisplayName("should throw an exception when the patch method receives a nonexistent id")
    void shouldThrowAnExceptionWhenThePatchMethodReceivesANonexistentId(){
        RequestUser requestUser = RequestUserBuilder.one().now();
        UserEntity userEntity = UserEntityBuilder.one().withRequestClient(requestUser).now();
        RequestUserUpdate clientUpdate = RequestClientUpdateBuilder.one().now();

        Assertions.assertThrows(ResponseStatusException.class, () -> userService
                .patch("2", clientUpdate));
    }

    @Test
    @DisplayName("Should bring all client")
    public void shouldFindAllClient(){
        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(UserEntityBuilder.one().now()));
        List<ResponseUser> listClient = userService.returnAllUsers();
        Assertions.assertNotNull(listClient);
        Assertions.assertEquals(1, listClient.size());
        Assertions.assertEquals("Jetosvaldo", listClient.get(0).getName());
    }

    @Test
    @DisplayName("Should bring a client by id")
    public void shouldFindClientById(){
        RequestUser requestUser = RequestUserBuilder.one().now();
        UserEntity userEntity = UserEntityBuilder.one().withRequestClient(requestUser).now();
        Mockito.when(userRepository.findById(userEntity.getId())).thenReturn(Optional.of(userEntity));
        userService.returnClientById(userEntity.getId());
        Mockito.verify(userRepository, Mockito.times(1)).findById(userEntity.getId());
        Assertions.assertEquals("Jetosvaldo", userEntity.getName());
    }

    @Test
    @DisplayName("Shouldn't find a client by id and should throw an exception")
    public void shouldNotFindClientById(){
        Assertions.assertThrows(ResponseStatusException.class, () -> userService
                .returnClientById("2"));
    }
}
