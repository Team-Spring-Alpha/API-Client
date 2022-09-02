package br.com.compass.filmes.cliente.dto.apiPayment.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestAuth {
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("api_key")
    private String apiKey;
}
