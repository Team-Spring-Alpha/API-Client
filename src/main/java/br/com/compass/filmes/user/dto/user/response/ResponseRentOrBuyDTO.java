package br.com.compass.filmes.user.dto.user.response;

import lombok.Data;

@Data
public class ResponseRentOrBuyDTO {
    private ResponseRentOrBuyDTO movies;
    private String crediCardNumber;
}
