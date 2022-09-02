package br.com.compass.filmes.cliente.builders;


import br.com.compass.filmes.cliente.dto.client.request.RequestCreditCard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RequestCreditCardBuilder {

    private RequestCreditCard requestCreditCard;

    public RequestCreditCardBuilder() {
    }

    public static RequestCreditCardBuilder one() {
        RequestCreditCardBuilder builder = new RequestCreditCardBuilder();
        builder.requestCreditCard = new RequestCreditCard();
        int yearNow = LocalDate.now().getYear();
        String yearNowString = Integer.toString(yearNow);

        builder.requestCreditCard.setClientCreditCardNumber("5181652903041943");
        builder.requestCreditCard.setClientCreditCardHolderName("Jetosvaldo");
        builder.requestCreditCard.setClientCreditCardBrand("MASTERCARD");
        builder.requestCreditCard.setClientCreditCardSecurityCode("613");
        builder.requestCreditCard.setClientCreditCardMonthExpiration("8");
        builder.requestCreditCard.setClientCreditCardYearExpiration(yearNowString);

        return builder;
    }

    public RequestCreditCardBuilder withClientCreditCardNumber(String clientCreditCardNumber) {
        this.requestCreditCard.setClientCreditCardNumber(clientCreditCardNumber);
        return this;
    }

    public RequestCreditCardBuilder withClientCreditCardHolderName(String clientCreditCardHolderName) {
        this.requestCreditCard.setClientCreditCardHolderName(clientCreditCardHolderName);
        return this;
    }

    public RequestCreditCardBuilder withClientCreditCardBrand(String clientCreditCardBrand) {
        this.requestCreditCard.setClientCreditCardBrand(clientCreditCardBrand);
        return this;
    }

    public RequestCreditCardBuilder withClientCreditCardSecurityCode(String clientCreditCardSecurityCode) {
        this.requestCreditCard.setClientCreditCardSecurityCode(clientCreditCardSecurityCode);
        return this;
    }

    public RequestCreditCardBuilder withClientCreditCardMonthExpiration(String clientCreditCardMonthExpiration) {
        this.requestCreditCard.setClientCreditCardMonthExpiration(clientCreditCardMonthExpiration);
        return this;
    }

    public RequestCreditCardBuilder withClientCreditCardYearExpiration(String clientCreditCardYearExpiration) {
        this.requestCreditCard.setClientCreditCardYearExpiration(clientCreditCardYearExpiration);
        return this;
    }

    public RequestCreditCard now() {
        return this.requestCreditCard;
    }

    public List<RequestCreditCard> list() {
        List<RequestCreditCard> creditCardList = new ArrayList<>();
        creditCardList.add(this.requestCreditCard);
        creditCardList.add(this.requestCreditCard);
        return creditCardList;
    }
}
