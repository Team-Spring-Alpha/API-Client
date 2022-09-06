package br.com.compass.filmes.user.controller;

import br.com.compass.filmes.user.dto.apiPayment.response.ResponseGatewayReproved;
import br.com.compass.filmes.user.dto.apiMovieManager.RequestMoviePayment;
import br.com.compass.filmes.user.service.MoviePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/movie-payment")
@RequiredArgsConstructor
public class MoviePaymentController {
    private final MoviePaymentService moviePaymentService;

    @PostMapping
    public ResponseEntity<ResponseGatewayReproved> post(@Valid @RequestBody RequestMoviePayment requestMoviePayment){
        ResponseGatewayReproved responseGateway = moviePaymentService.post(requestMoviePayment);
        if(responseGateway.getPaymentStatus().equals("REPROVED")){
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(responseGateway);
        }
        return ResponseEntity.ok().body(responseGateway);
    }
}
