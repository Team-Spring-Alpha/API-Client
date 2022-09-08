package br.com.compass.filmes.user.service;

import br.com.compass.filmes.user.dto.security.AccountCredentials;
import br.com.compass.filmes.user.dto.security.Token;
import br.com.compass.filmes.user.entities.UserEntity;
import br.com.compass.filmes.user.exceptions.UserAuthInvalidException;
import br.com.compass.filmes.user.repository.UserRepository;
import br.com.compass.filmes.user.security.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository repository;

    public ResponseEntity signin(AccountCredentials data) {
        try {
            String email = data.getEmail();
            String password = data.getPassword();

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));

            UserEntity user = repository.findByEmail(email);
            var tokenResponse = new Token();
            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(email, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Email " + email + " not found!");
            }
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new UserAuthInvalidException("Invalid email / password supplied!");
        }
    }

    public ResponseEntity refreshToken(String email, String refreshToken) {
        var user = repository.findByEmail(email);
        var tokenResponse = new Token();
        if (user != null) {
            tokenResponse = tokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Email " + email + " not found!");
        }
        return ResponseEntity.ok(tokenResponse);
    }
}
