package br.com.compass.filmes.cliente.dto.apiPayment.response;

import lombok.Data;

@Data
public class ResponseMoviePaymentProcess {
    private Long movieId;
    private String title;
    private String link;
}
