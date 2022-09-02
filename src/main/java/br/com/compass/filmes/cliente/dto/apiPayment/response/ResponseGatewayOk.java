package br.com.compass.filmes.cliente.dto.apiPayment.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResponseGatewayOk extends ResponseGatewayReproved{
    private List<ResponseMoviePaymentProcess> movies;
}
