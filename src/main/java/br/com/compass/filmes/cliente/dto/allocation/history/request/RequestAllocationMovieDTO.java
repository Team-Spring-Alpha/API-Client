package br.com.compass.filmes.cliente.dto.allocation.history.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestAllocationMovieDTO {
    private Long id;
    private String name;
}
