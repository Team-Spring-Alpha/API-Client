package br.com.compass.filmes.user.dto.movie.manager;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RequestRentOrBuyDTO {
    @NotNull
    @NotEmpty
    private List<Long> rent;
    @NotNull
    @NotEmpty
    private List<Long> buy;
}
