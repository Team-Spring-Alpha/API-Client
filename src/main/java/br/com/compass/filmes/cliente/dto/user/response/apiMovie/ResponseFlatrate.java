package br.com.compass.filmes.cliente.dto.user.response.apiMovie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseFlatrate {
    @JsonProperty("provider_name")
    private String providerName;
}
