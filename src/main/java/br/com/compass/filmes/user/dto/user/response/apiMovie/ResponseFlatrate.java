package br.com.compass.filmes.user.dto.user.response.apiMovie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseFlatrate {
    @JsonProperty("provider_name")
    private String providerName;
}
