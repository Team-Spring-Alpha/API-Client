package br.com.compass.filmes.user.dto.user.response;

import lombok.Data;

@Data
public class ResponseRentOrBuy {
    private ResponseRentOrBuy movies;
    private String crediCardNumber;
}
