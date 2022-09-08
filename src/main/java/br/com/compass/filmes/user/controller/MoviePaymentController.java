package br.com.compass.filmes.user.controller;

import br.com.compass.filmes.user.dto.movie.manager.RequestMoviePaymentDTO;
import br.com.compass.filmes.user.dto.payment.response.ResponseGatewayReprovedDTO;
import br.com.compass.filmes.user.service.MoviePaymentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "send a allocation request")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @PostMapping
    public ResponseEntity<ResponseGatewayReprovedDTO> post(@Valid @RequestBody RequestMoviePaymentDTO requestMoviePaymentDTO){
        ResponseGatewayReprovedDTO responseGateway = moviePaymentService.post(requestMoviePaymentDTO);
        if(responseGateway.getPaymentStatus().equals("REPROVED")){
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(responseGateway);
        }
        return ResponseEntity.ok().body(responseGateway);
    }
}
