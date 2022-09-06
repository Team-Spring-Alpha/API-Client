package br.com.compass.filmes.user.dto.payment.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ResponseGatewayOk extends ResponseGatewayReproved{
    private final List<ResponseMoviePaymentProcess> movies;

    public ResponseGatewayOk(List<ResponseMoviePaymentProcess> moviePaymentProcessList) {
        super("APPROVED", "approved");
        this.movies = moviePaymentProcessList;
    }
}
