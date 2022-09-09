package br.com.compass.filmes.user.service;

import br.com.compass.filmes.user.builders.UserEntityBuilder;
import br.com.compass.filmes.user.dto.security.AccountCredentialsDTO;
import br.com.compass.filmes.user.dto.security.TokenDTO;
import br.com.compass.filmes.user.entities.UserEntity;
import br.com.compass.filmes.user.exceptions.UserAuthInvalidException;
import br.com.compass.filmes.user.repository.UserRepository;
import br.com.compass.filmes.user.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = AuthService.class)
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtTokenProvider tokenProvider;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Should get access token")
    void shouldGetTokenAcessSuccessful() {
        AccountCredentialsDTO accountCredentialsDTO = new AccountCredentialsDTO("email teste", "password");
        UserEntity userEntity = UserEntityBuilder.one().now();
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setAccessToken("access test");

        Mockito.when(userRepository.findByEmail(accountCredentialsDTO.getEmail())).thenReturn(Optional.ofNullable(userEntity));
        Mockito.when(tokenProvider.createAccessToken(accountCredentialsDTO.getEmail(), userEntity.getRoles())).thenReturn(tokenDTO);

        TokenDTO tokenSignin = authService.signin(accountCredentialsDTO);

        Assertions.assertEquals(tokenDTO, tokenSignin);
    }

    @Test
    @DisplayName("Should throw exception when userEmail is invalid")
    void shouldThrowExceptionWhenUserEmailIsInvalid() {
        AccountCredentialsDTO accountCredentialsDTO = new AccountCredentialsDTO("email teste", "password");

        Assertions.assertThrows(UserAuthInvalidException.class, () -> authService.signin(accountCredentialsDTO));
    }

    @Test
    @DisplayName("Should refresh access token")
    void refreshToken() {
        UserEntity userEntity = UserEntityBuilder.one().now();
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setAccessToken("token");

        Mockito.when(userRepository.findByEmail(any())).thenReturn(Optional.ofNullable(userEntity));
        Mockito.when(tokenProvider.refreshToken(any())).thenReturn(tokenDTO);

        TokenDTO tokenRefresh = authService.refreshToken("email test", "token teste");

        Assertions.assertEquals(tokenDTO, tokenRefresh);
    }

    @Test
    @DisplayName("should throw exception when either info are null or emptied")
    void shouldThrowExceptionWhenEitherInfoAreNullOrEmptied() {
        Assertions.assertThrows(ResponseStatusException.class, () -> authService.refreshToken(null, ""));
    }

    @Test
    @DisplayName("should throw exception when not found a user")
    void shouldThrowExceptionWhenNotFoundAUserByEmail() {
        Assertions.assertThrows(ResponseStatusException.class, () -> authService.refreshToken("teste", ""));
    }

    @Test
    @DisplayName("should throw exception when not get a token")
    void shouldThrowExceptionWhenNotGetAToken() {
        UserEntity userEntity = UserEntityBuilder.one().now();

        Mockito.when(userRepository.findByEmail(any())).thenReturn(Optional.ofNullable(userEntity));
        Mockito.when(tokenProvider.refreshToken(any())).thenThrow(new RuntimeException());

        Assertions.assertThrows(ResponseStatusException.class, () -> authService.refreshToken("teste", "teste"));
    }
}