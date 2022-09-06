package br.com.compass.filmes.user.dto.user.response;

import lombok.Data;


@Data
public class ResponseMoviePayment {

    private ResponseRentOrBuy movies;
    private String creditCardNumber;

}
