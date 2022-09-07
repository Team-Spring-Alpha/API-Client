package br.com.compass.filmes.cliente.dto.user.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestSetStatusUserAccount {
    @NotNull
    private boolean clientIsBlocked;
}
