package br.com.compass.filmes.user.dto.allocation.history;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestAllocationMovieDTO {
    private Long id;
    private String name;
}
