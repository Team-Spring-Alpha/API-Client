package br.com.compass.filmes.cliente.builders;

import br.com.compass.filmes.cliente.dto.movie.manager.RequestRentOrBuyDTO;

import java.util.ArrayList;
import java.util.List;

public class RequestRentOrBuyBuilder {

    private RequestRentOrBuyDTO requestRentOrBuyDTO;

    public RequestRentOrBuyBuilder() {
    }

    public static RequestRentOrBuyBuilder one() {
        RequestRentOrBuyBuilder builder = new RequestRentOrBuyBuilder();
        builder.requestRentOrBuyDTO = new RequestRentOrBuyDTO();

        List<Long> buyList = new ArrayList<>();
        buyList.add(1L);
        buyList.add(2L);

        List<Long> rentList = new ArrayList<>();
        rentList.add(5L);
        rentList.add(6L);

        builder.requestRentOrBuyDTO.setBuy(buyList);
        builder.requestRentOrBuyDTO.setRent(rentList);

        return builder;
    }

    public RequestRentOrBuyBuilder withBuyList(List<Long> buyList) {
        this.requestRentOrBuyDTO.setBuy(buyList);
        return this;
    }

    public RequestRentOrBuyBuilder withRentList(List<Long> rentList) {
        this.requestRentOrBuyDTO.setRent(rentList);
        return this;
    }

    public RequestRentOrBuyDTO now() {
        return this.requestRentOrBuyDTO;
    }
}
