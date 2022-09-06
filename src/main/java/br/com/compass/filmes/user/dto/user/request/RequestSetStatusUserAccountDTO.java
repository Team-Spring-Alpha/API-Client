package br.com.compass.filmes.user.dto.user.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestSetStatusUserAccountDTO {
    @NotNull
    private boolean isBlocked;
}
