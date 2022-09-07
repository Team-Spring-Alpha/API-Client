package br.com.compass.filmes.cliente.dto.user.response;

import lombok.Data;

@Data
public class ResponseRentOrBuy {
    private ResponseRentOrBuy movies;
    private String crediCardNumber;
}
