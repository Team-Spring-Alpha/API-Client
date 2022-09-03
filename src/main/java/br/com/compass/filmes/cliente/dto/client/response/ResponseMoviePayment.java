package br.com.compass.filmes.cliente.dto.client.response;

import lombok.Data;


@Data
public class ResponseMoviePayment {

    private ResponseRentOrBuy movies;
    private String creditCardNumber;

}
