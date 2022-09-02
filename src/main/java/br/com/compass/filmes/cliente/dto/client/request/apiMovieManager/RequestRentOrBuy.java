package br.com.compass.filmes.cliente.dto.client.request.apiMovieManager;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RequestRentOrBuy {
    @NotNull
    @NotEmpty
    private List<String> rent;
    @NotNull
    @NotEmpty
    private List<String> buy;
}
