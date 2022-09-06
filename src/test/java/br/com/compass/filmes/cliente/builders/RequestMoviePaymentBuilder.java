package br.com.compass.filmes.cliente.builders;

import br.com.compass.filmes.cliente.dto.apiMovieManager.RequestMoviePayment;
import br.com.compass.filmes.cliente.dto.apiMovieManager.RequestRentOrBuy;

public class RequestMoviePaymentBuilder {

    private RequestMoviePayment requestMoviePayment;

    public RequestMoviePaymentBuilder() {
    }

    public static RequestMoviePaymentBuilder one() {
        RequestMoviePaymentBuilder builder = new RequestMoviePaymentBuilder();
        builder.requestMoviePayment = new RequestMoviePayment();

        RequestRentOrBuy requestRentOrBuy = RequestRentOrBuyBuilder.one().now();

        builder.requestMoviePayment.setUserId("test");
        builder.requestMoviePayment.setCreditCardNumber("card test");
        builder.requestMoviePayment.setMovies(requestRentOrBuy);

        return builder;
    }

    public RequestMoviePaymentBuilder withUserId(String userId) {
        this.requestMoviePayment.setUserId(userId);
        return this;
    }

    public RequestMoviePaymentBuilder withCreditCardNumber(String cardNumber) {
        this.requestMoviePayment.setCreditCardNumber(cardNumber);
        return this;
    }

    public RequestMoviePaymentBuilder withRentOrBuy(RequestRentOrBuy rentOrBuy) {
        this.requestMoviePayment.setMovies(rentOrBuy);
        return this;
    }

    public RequestMoviePayment now() {
        return this.requestMoviePayment;
    }
}
