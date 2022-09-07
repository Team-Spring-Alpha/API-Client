package br.com.compass.filmes.cliente.dto.apiMovie;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseRentAndBuyDTO {
    private String store;
    private Double price;
}
