package br.com.compass.filmes.cliente.dto.apiPayment.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ResponseGatewayOkDTO extends ResponseGatewayReprovedDTO {
    private final List<ResponseMoviePaymentProcessDTO> movies;

    public ResponseGatewayOkDTO(List<ResponseMoviePaymentProcessDTO> moviePaymentProcessList) {
        super("APPROVED", "approved");
        this.movies = moviePaymentProcessList;
    }
}
