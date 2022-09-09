package br.com.compass.filmes.user.builders;

import br.com.compass.filmes.user.dto.moviepayment.RequestMoviePaymentDTO;
import br.com.compass.filmes.user.dto.moviepayment.RequestRentOrBuyDTO;

import java.util.ArrayList;
import java.util.List;

public class RequestMoviePaymentBuilder {

    private RequestMoviePaymentDTO requestMoviePaymentDTO;

    public RequestMoviePaymentBuilder() {
    }

    public static RequestMoviePaymentBuilder one() {
        RequestMoviePaymentBuilder builder = new RequestMoviePaymentBuilder();
        builder.requestMoviePaymentDTO = new RequestMoviePaymentDTO();

        RequestRentOrBuyDTO buyAndRent = RequestRentOrBuyBuilder.one().now();
        List<RequestRentOrBuyDTO> buyAndRentList = new ArrayList<>();
        buyAndRentList.add(buyAndRent);

        builder.requestMoviePaymentDTO.setCreditCardNumber("card test");
        builder.requestMoviePaymentDTO.setMoviesBuy(buyAndRentList);
        builder.requestMoviePaymentDTO.setMoviesRent(buyAndRentList);

        return builder;
    }

    public RequestMoviePaymentBuilder withCreditCardNumber(String cardNumber) {
        this.requestMoviePaymentDTO.setCreditCardNumber(cardNumber);
        return this;
    }

    public RequestMoviePaymentBuilder withRentList(List<RequestRentOrBuyDTO> rentList) {
        this.requestMoviePaymentDTO.setMoviesRent(rentList);
        return this;
    }

    public RequestMoviePaymentBuilder withBuyList(List<RequestRentOrBuyDTO> buyList) {
        this.requestMoviePaymentDTO.setMoviesRent(rentList);
        return this;
    }

    public RequestMoviePaymentDTO now() {
        return this.requestMoviePaymentDTO;
    }
}
