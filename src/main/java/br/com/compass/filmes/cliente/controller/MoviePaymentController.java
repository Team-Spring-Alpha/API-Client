package br.com.compass.filmes.cliente.controller;

import br.com.compass.filmes.cliente.dto.client.request.apiMovieManager.RequestMoviePayment;
import br.com.compass.filmes.cliente.dto.client.response.ResponseMoviePayment;
import br.com.compass.filmes.cliente.service.MoviePaymentService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ResponseMoviePayment> post(@Valid @RequestBody RequestMoviePayment requestMoviePayment){
        ResponseMoviePayment responseMoviePayment = moviePaymentService.post(requestMoviePayment);
        return ResponseEntity.ok().body(responseMoviePayment);
    }
}
