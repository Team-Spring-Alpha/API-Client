package br.com.compass.filmes.cliente.dto.client.response.apiMovie;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseJustWatch {
    private List<ResponseRentAndBuy> rent;
    private List<ResponseRentAndBuy> buy;
    private List<ResponseFlatrate> flatrate;
}
