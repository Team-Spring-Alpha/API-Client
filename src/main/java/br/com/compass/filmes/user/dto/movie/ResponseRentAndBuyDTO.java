package br.com.compass.filmes.user.dto.movie;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseRentAndBuyDTO {
    private String store;
    private Double price;
}
