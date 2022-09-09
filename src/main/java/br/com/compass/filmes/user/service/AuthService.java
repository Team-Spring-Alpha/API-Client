package br.com.compass.filmes.user.service;

import br.com.compass.filmes.user.dto.security.AccountCredentialsDTO;
import br.com.compass.filmes.user.dto.security.TokenDTO;
import br.com.compass.filmes.user.entities.UserEntity;
import br.com.compass.filmes.user.exceptions.UserAuthInvalidException;
import br.com.compass.filmes.user.repository.UserRepository;
import br.com.compass.filmes.user.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository repository;

    public TokenDTO signin(AccountCredentialsDTO data) {
        try {
            String email = data.getEmail();
            String password = data.getPassword();

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));

            UserEntity user = repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email " + email + " not found!"));
            return tokenProvider.createAccessToken(email, user.getRoles());
        } catch (Exception e) {
            throw new UserAuthInvalidException("Invalid email / password supplied!");
        }
    }

    public TokenDTO refreshToken(String email, String refreshToken) {
        boolean isInvalidParams = allParamsIsNull(email, refreshToken);
        if (isInvalidParams) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        UserEntity user = repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email " + email + " not found!"));
        try {
            return tokenProvider.refreshToken(refreshToken);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    private boolean allParamsIsNull(String email, String refreshToken) {
        return Objects.isNull(email) || email.isBlank() || Objects.isNull(refreshToken) || refreshToken.isBlank();
    }
}
