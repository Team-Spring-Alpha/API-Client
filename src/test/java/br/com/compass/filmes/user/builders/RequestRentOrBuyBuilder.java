package br.com.compass.filmes.user.builders;

import br.com.compass.filmes.user.dto.apiMovieManager.RequestRentOrBuy;

import java.util.ArrayList;
import java.util.List;

public class RequestRentOrBuyBuilder {

    private RequestRentOrBuy requestRentOrBuy;

    public RequestRentOrBuyBuilder() {
    }

    public static RequestRentOrBuyBuilder one() {
        RequestRentOrBuyBuilder builder = new RequestRentOrBuyBuilder();
        builder.requestRentOrBuy = new RequestRentOrBuy();

        List<Long> buyList = new ArrayList<>();
        buyList.add(1L);
        buyList.add(2L);

        List<Long> rentList = new ArrayList<>();
        rentList.add(5L);
        rentList.add(6L);

        builder.requestRentOrBuy.setBuy(buyList);
        builder.requestRentOrBuy.setRent(rentList);

        return builder;
    }

    public RequestRentOrBuyBuilder withBuyList(List<Long> buyList) {
        this.requestRentOrBuy.setBuy(buyList);
        return this;
    }

    public RequestRentOrBuyBuilder withRentList(List<Long> rentList) {
        this.requestRentOrBuy.setRent(rentList);
        return this;
    }

    public RequestRentOrBuy now() {
        return this.requestRentOrBuy;
    }
}
