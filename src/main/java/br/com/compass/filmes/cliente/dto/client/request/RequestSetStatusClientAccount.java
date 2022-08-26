package br.com.compass.filmes.cliente.dto.client.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestSetStatusClientAccount {
    @NotNull
    private boolean clientIsBlocked;
}
