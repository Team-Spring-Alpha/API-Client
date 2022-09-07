package br.com.compass.filmes.cliente.dto.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseFlatrateDTO {
    @JsonProperty("provider_name")
    private String providerName;
}
