package br.com.compass.filmes.user.service;

import br.com.compass.filmes.user.builders.*;
import br.com.compass.filmes.user.dto.user.request.RequestUserDTO;
import br.com.compass.filmes.user.dto.user.request.RequestUserUpdateDTO;
import br.com.compass.filmes.user.dto.user.request.RequestCreditCardDTO;
import br.com.compass.filmes.user.dto.user.request.RequestSetStatusUserAccountDTO;
import br.com.compass.filmes.user.dto.user.response.ResponseUserDTO;
import br.com.compass.filmes.user.entities.UserEntity;
import br.com.compass.filmes.user.repository.UserRepository;
import br.com.compass.filmes.user.util.EncriptPasswordUtil;
import br.com.compass.filmes.user.util.ValidateRequestUserUtil;
import br.com.compass.filmes.user.util.ValidateRequestCreditCardUtil;
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
    private EncriptPasswordUtil encriptPasswordUtil;

    @SpyBean
    private ModelMapper modelMapper;

    @SpyBean
    private ValidateRequestCreditCardUtil validateRequestCreditCardUtil;

    @SpyBean
    private ValidateRequestUserUtil validateRequestUserUtil;

    @Test
    @DisplayName("should successful create a user")
    void shouldSuccesfulCreateAUser() {
        RequestUserDTO requestUserDTO = RequestUserBuilder.one().now();
        UserEntity userEntity = UserEntityBuilder.one().withRequestClient(requestUserDTO).now();

        Mockito.when(userRepository.save(any())).thenReturn(userEntity);

        ResponseUserDTO responseUserDTO = userService.post(requestUserDTO);

        Assertions.assertEquals(userEntity.getName(), responseUserDTO.getName());
        Assertions.assertEquals(userEntity.getBirthDate(), responseUserDTO.getBirthDate());
        Assertions.assertEquals(userEntity.getCards().get(0).getBrand(), responseUserDTO.getCards().get(0).getBrand());
        Assertions.assertEquals(userEntity.getCards().get(1).getBrand(), responseUserDTO.getCards().get(1).getBrand());
    }

    @Test
    @DisplayName("should throw exception when create a user with a wrong category")
    void shouldThrowExceptionWhenCreateAUserWithWrongCategory() {
        List<String> categoriesList = new ArrayList<>();
        categoriesList.add("test");

        RequestUserDTO requestUserDTO = RequestUserBuilder.one()
                .withClientCategory(categoriesList)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> userService.post(requestUserDTO));
    }

    @Test
    @DisplayName("should throw exception when create a user with a wrong credit card brand")
    void shouldThrowExceptionWhenCreateAUserWithWrongCreditCardBrand() {
        List<RequestCreditCardDTO> requestCreditCardDTO = RequestCreditCardBuilder.one()
                .withClientCreditCardBrand("test")
                .list();

        RequestUserDTO requestUserDTO = RequestUserBuilder.one()
                .withCreditCards(requestCreditCardDTO)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> userService.post(requestUserDTO));
    }

    @Test
    @DisplayName("should throw exception when create a user with a wrong credit card security code")
    void shouldThrowExceptionWhenCreateAUserWithWrongCreditCardSecurityCode() {
        List<RequestCreditCardDTO> requestCreditCardDTO = RequestCreditCardBuilder.one()
                .withClientCreditCardSecurityCode("01x")
                .list();

        RequestUserDTO requestUserDTO = RequestUserBuilder.one()
                .withCreditCards(requestCreditCardDTO)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> userService.post(requestUserDTO));
    }

    @Test
    @DisplayName("should throw exception when create a user with a wrong credit card security code (missing 1 digit)")
    void shouldThrowExceptionWhenCreateAUserWithCreditCardSecurityCodeMissingOneDigit() {
        List<RequestCreditCardDTO> requestCreditCardDTO = RequestCreditCardBuilder.one()
                .withClientCreditCardSecurityCode("01")
                .list();

        RequestUserDTO requestUserDTO = RequestUserBuilder.one()
                .withCreditCards(requestCreditCardDTO)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> userService.post(requestUserDTO));
    }

    @Test
    @DisplayName("should throw exception when create a user with a wrong credit card month")
    void shouldThrowExceptionWhenCreateAUserWithWrongCreditCardMonth() {
        List<RequestCreditCardDTO> requestCreditCardDTO = RequestCreditCardBuilder.one()
                .withClientCreditCardMonthExpiration("01")
                .list();

        RequestUserDTO requestUserDTO = RequestUserBuilder.one()
                .withCreditCards(requestCreditCardDTO)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> userService.post(requestUserDTO));
        requestUserDTO.getCards().get(0).setMonthExpiration("teste");
        Assertions.assertThrows(ResponseStatusException.class, () -> userService.post(requestUserDTO));
        requestUserDTO.getCards().get(0).setMonthExpiration("13");
        Assertions.assertThrows(ResponseStatusException.class, () -> userService.post(requestUserDTO));
        requestUserDTO.getCards().get(0).setMonthExpiration("-3");
        Assertions.assertThrows(ResponseStatusException.class, () -> userService.post(requestUserDTO));
    }

    @Test
    @DisplayName("should throw exception when create a user with a wrong credit card year (+6 year)")
    void shouldThrowExceptionWhenCreateUserWithWrongCreditCardYearAfterSixYears() {
        String yearString = LocalDate.now().plusYears(6).toString();
        List<RequestCreditCardDTO> requestCreditCardDTO = RequestCreditCardBuilder.one()
                .withClientCreditCardYearExpiration(yearString)
                .list();

        RequestUserDTO requestUserDTO = RequestUserBuilder.one()
                .withCreditCards(requestCreditCardDTO)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> userService.post(requestUserDTO));
    }

    @Test
    @DisplayName("should throw exception when create a user with a wrong credit card year (-1 year)")
    void shouldThrowExceptionWhenCreateUserWithCreditCardYearBeforeNow() {
        String yearString = LocalDate.now().minusYears(1).toString();
        List<RequestCreditCardDTO> requestCreditCardDTO = RequestCreditCardBuilder.one()
                .withClientCreditCardYearExpiration(yearString)
                .list();

        RequestUserDTO requestUserDTO = RequestUserBuilder.one()
                .withCreditCards(requestCreditCardDTO)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> userService.post(requestUserDTO));
    }

    @Test
    @DisplayName("should throw exception when create a user with a wrong credit card year")
    void shouldThrowExceptionWhenCreateUserWithCreditCardYear() {
        String yearString = "teste";
        List<RequestCreditCardDTO> requestCreditCardDTO = RequestCreditCardBuilder.one()
                .withClientCreditCardYearExpiration(yearString)
                .list();

        RequestUserDTO requestUserDTO = RequestUserBuilder.one()
                .withCreditCards(requestCreditCardDTO)
                .now();

        Assertions.assertThrows(ResponseStatusException.class, () -> userService.post(requestUserDTO));
    }

    @Test
    @DisplayName("should set the user's status account as blocked")
    void shouldSetTheUserStatusAccountAsBlocked(){
        RequestUserDTO requestUserDTO = RequestUserBuilder.one().now();
        UserEntity userEntity = UserEntityBuilder.one().withRequestClient(requestUserDTO).now();
        RequestSetStatusUserAccountDTO requestSetStatusUserAccountDTO =
                RequestSetStatusUserAccountBuilder.blocked().now();

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));

        ResponseUserDTO responseUserDTO = userService.setStatusUserAccount("1",
                requestSetStatusUserAccountDTO);

        Mockito.verify(userRepository).save(userEntity);
        Assertions.assertTrue(responseUserDTO.isBlocked());
    }
    @Test
    @DisplayName("should set the user's status account as unlocked")
    void shouldSetTheUserStatusAccountAsUnlocked(){
        RequestUserDTO requestUserDTO = RequestUserBuilder.one().now();
        UserEntity userEntity = UserEntityBuilder.one().withRequestClient(requestUserDTO).now();
        RequestSetStatusUserAccountDTO requestSetStatusUserAccountDTO =
                RequestSetStatusUserAccountBuilder.unlocked().now();

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));

        ResponseUserDTO responseUserDTO = userService.setStatusUserAccount("1",
                requestSetStatusUserAccountDTO);

        Mockito.verify(userRepository).save(userEntity);
        Assertions.assertFalse(responseUserDTO.isBlocked());
    }
    @Test
    @DisplayName("should throw an exception when the setStatusUserAccount method receives a nonexistent id")
    void shouldThrowAnExceptionWhenTheSetStatusUserAccountMethodReceivesANonexistentId(){
        RequestSetStatusUserAccountDTO requestSetStatusUserAccountDTO =
                RequestSetStatusUserAccountBuilder.blocked().now();

        Assertions.assertThrows(ResponseStatusException.class, () -> userService
                .setStatusUserAccount("2", requestSetStatusUserAccountDTO));
    }

    @Test
    @DisplayName("should successful update a user")
    void shouldSuccessfulUpdateAUser(){
        RequestUserDTO requestUserDTO = RequestUserBuilder.one().now();
        UserEntity userEntity = UserEntityBuilder.one().withRequestClient(requestUserDTO).now();
        RequestUserUpdateDTO userUpdate = RequestUserUpdateBuilder.one().now();

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));
        Mockito.when(userRepository.save(any())).thenReturn(userEntity);

        ResponseUserDTO responseUserDTO = userService.patch("idTeste", userUpdate);

        Assertions.assertEquals(userEntity.getEmail(), userUpdate.getEmail());
        Assertions.assertEquals(userEntity.getPassword(), userUpdate.getPassword());
        Assertions.assertEquals(userEntity.getName(), responseUserDTO.getName());
        Assertions.assertEquals(userEntity.getBirthDate(), responseUserDTO.getBirthDate());
    }

    @Test
    @DisplayName("should throw an exception when the patch method receives a nonexistent id")
    void shouldThrowAnExceptionWhenThePatchMethodReceivesANonexistentId(){
        RequestUserUpdateDTO clientUpdate = RequestUserUpdateBuilder.one().now();

        Assertions.assertThrows(ResponseStatusException.class, () -> userService
                .patch("2", clientUpdate));
    }

    @Test
    @DisplayName("Should bring all users")
    public void shouldFindAllUsers(){
        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(UserEntityBuilder.one().now()));
        List<ResponseUserDTO> userList = userService.returnAllUsers();
        Assertions.assertNotNull(userList);
        Assertions.assertEquals(1, userList.size());
        Assertions.assertEquals("Jetosvaldo", userList.get(0).getName());
    }

    @Test
    @DisplayName("Should bring a user by id")
    public void shouldFindUserById(){
        RequestUserDTO requestUserDTO = RequestUserBuilder.one().now();
        UserEntity userEntity = UserEntityBuilder.one().withRequestClient(requestUserDTO).now();
        Mockito.when(userRepository.findById(userEntity.getId())).thenReturn(Optional.of(userEntity));
        userService.returnUserById(userEntity.getId());
        Mockito.verify(userRepository, Mockito.times(1)).findById(userEntity.getId());
        Assertions.assertEquals("Jetosvaldo", userEntity.getName());
    }

    @Test
    @DisplayName("Shouldn't find a user by id and should throw an exception")
    public void shouldNotFindUserById(){
        Assertions.assertThrows(ResponseStatusException.class, () -> userService
                .returnUserById("2"));
    }
}
