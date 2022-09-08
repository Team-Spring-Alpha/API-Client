package br.com.compass.filmes.user.controller;

import br.com.compass.filmes.user.dto.security.AccountCredentials;
import br.com.compass.filmes.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody AccountCredentials data) {
        if (checkIfParamsIsNull(data))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        var token = authService.signin(data);
        if (token == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return token;
    }


    @PutMapping(value = "/refresh/{username}")
    public ResponseEntity refreshToken(@PathVariable("email") String email,
                                       @RequestHeader("Authorization") String refreshToken) {
        if (checkIfParamsIsNull(email, refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        var token = authService.refreshToken(email, refreshToken);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;
    }

    private boolean checkIfParamsIsNull(String username, String refreshToken) {
        return refreshToken == null || refreshToken.isBlank() ||
                username == null || username.isBlank();
    }

    private boolean checkIfParamsIsNull(AccountCredentials data) {
        return data == null || data.getEmail() == null || data.getEmail().isBlank()
                || data.getPassword() == null || data.getPassword().isBlank();
    }
}
