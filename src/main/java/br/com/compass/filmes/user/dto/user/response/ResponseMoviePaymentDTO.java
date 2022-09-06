package br.com.compass.filmes.user.dto.user.response;

import lombok.Data;


@Data
public class ResponseMoviePaymentDTO {

    private ResponseRentOrBuyDTO movies;
    private String creditCardNumber;

}
