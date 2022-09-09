package br.com.compass.filmes.user.controller;

import br.com.compass.filmes.user.dto.security.AccountCredentialsDTO;
import br.com.compass.filmes.user.dto.security.TokenDTO;
import br.com.compass.filmes.user.service.AuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @ApiOperation(value = "get access token to payment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PostMapping(value = "/signin")
    public ResponseEntity<TokenDTO> signin(@Valid @RequestBody AccountCredentialsDTO data) {
        TokenDTO tokenDTO = authService.signin(data);
        return ResponseEntity.ok(tokenDTO);
    }

    @ApiOperation(value = "get refresh access token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @PutMapping(value = "/refresh/{email}")
    public ResponseEntity<TokenDTO> refreshToken(@PathVariable("email") String email,
                                                 @RequestHeader("Authorization") String refreshToken) {
        TokenDTO tokenDTO = authService.refreshToken(email, refreshToken);
        return ResponseEntity.ok(tokenDTO);
    }
}
