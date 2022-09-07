package br.com.compass.filmes.cliente.dto.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestSetStatusUserAccountDTO {
    @NotNull
    @JsonProperty("user_is_blocked")
    private boolean userIsBlocked;
}
