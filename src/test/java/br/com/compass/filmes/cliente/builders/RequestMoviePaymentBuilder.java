package br.com.compass.filmes.cliente.builders;

import br.com.compass.filmes.cliente.dto.movie.manager.RequestMoviePaymentDTO;
import br.com.compass.filmes.cliente.dto.movie.manager.RequestRentOrBuyDTO;

public class RequestMoviePaymentBuilder {

    private RequestMoviePaymentDTO requestMoviePaymentDTO;

    public RequestMoviePaymentBuilder() {
    }

    public static RequestMoviePaymentBuilder one() {
        RequestMoviePaymentBuilder builder = new RequestMoviePaymentBuilder();
        builder.requestMoviePaymentDTO = new RequestMoviePaymentDTO();

        RequestRentOrBuyDTO requestRentOrBuyDTO = RequestRentOrBuyBuilder.one().now();

        builder.requestMoviePaymentDTO.setUserId("test");
        builder.requestMoviePaymentDTO.setCreditCardNumber("card test");
        builder.requestMoviePaymentDTO.setMovies(requestRentOrBuyDTO);

        return builder;
    }

    public RequestMoviePaymentBuilder withUserId(String userId) {
        this.requestMoviePaymentDTO.setUserId(userId);
        return this;
    }

    public RequestMoviePaymentBuilder withCreditCardNumber(String cardNumber) {
        this.requestMoviePaymentDTO.setCreditCardNumber(cardNumber);
        return this;
    }

    public RequestMoviePaymentBuilder withRentOrBuy(RequestRentOrBuyDTO rentOrBuy) {
        this.requestMoviePaymentDTO.setMovies(rentOrBuy);
        return this;
    }

    public RequestMoviePaymentDTO now() {
        return this.requestMoviePaymentDTO;
    }
}
