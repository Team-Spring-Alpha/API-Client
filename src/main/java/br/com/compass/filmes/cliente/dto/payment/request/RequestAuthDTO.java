package br.com.compass.filmes.cliente.dto.payment.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class RequestAuthDTO {
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("api_key")
    private String apiKey;
}
