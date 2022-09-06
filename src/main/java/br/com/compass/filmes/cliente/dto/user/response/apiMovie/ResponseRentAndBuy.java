package br.com.compass.filmes.cliente.dto.user.response.apiMovie;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseRentAndBuy {
    private String store;
    private Double price;
}
