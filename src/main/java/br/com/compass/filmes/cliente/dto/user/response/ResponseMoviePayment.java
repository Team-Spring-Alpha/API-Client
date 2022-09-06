package br.com.compass.filmes.cliente.dto.user.response;

import lombok.Data;


@Data
public class ResponseMoviePayment {

    private ResponseRentOrBuy movies;
    private String creditCardNumber;

}
