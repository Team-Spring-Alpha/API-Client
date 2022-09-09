package br.com.compass.filmes.user.builders;

import br.com.compass.filmes.user.dto.moviepayment.RequestRentOrBuyDTO;

public class RequestRentOrBuyBuilder {

    private RequestRentOrBuyDTO requestRentOrBuyDTO;

    public RequestRentOrBuyBuilder() {
    }

    public static RequestRentOrBuyBuilder one() {
        RequestRentOrBuyBuilder builder = new RequestRentOrBuyBuilder();
        builder.requestRentOrBuyDTO = new RequestRentOrBuyDTO();

        builder.requestRentOrBuyDTO.setId(1L);
        builder.requestRentOrBuyDTO.setStore("Netflix");

        return builder;
    }

    public RequestRentOrBuyBuilder withId(Long id) {
        this.requestRentOrBuyDTO.setId(id);
        return this;
    }

    public RequestRentOrBuyBuilder withStore(String store) {
        this.requestRentOrBuyDTO.setStore(store);
        return this;
    }

    public RequestRentOrBuyDTO now() {
        return this.requestRentOrBuyDTO;
    }
}
