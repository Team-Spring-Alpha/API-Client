package br.com.compass.filmes.cliente.dto.apiPayment.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseAuthDTO {

    @JsonProperty("access_token")
    private String token;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private String expiresIn;
}
