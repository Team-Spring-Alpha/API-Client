package br.com.compass.filmes.cliente.dto.apiAllocationHistory;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestAllocationMovie {
    private Long id;
    private String name;
}
