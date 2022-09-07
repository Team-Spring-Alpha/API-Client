package br.com.compass.filmes.cliente.dto.apiPayment.response;

import lombok.Data;

@Data
public class ResponseMoviePaymentProcessDTO {
    private Long movieId;
    private String title;
    private String link;
}
