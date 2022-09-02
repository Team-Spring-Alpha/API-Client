package br.com.compass.filmes.cliente.dto.client.response;

import lombok.Data;

@Data
public class ResponseRentOrBuy {
    private ResponseRentOrBuy movies;
    private String crediCardNumber;
}
