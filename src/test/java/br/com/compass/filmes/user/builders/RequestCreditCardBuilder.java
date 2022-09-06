package br.com.compass.filmes.user.builders;


import br.com.compass.filmes.user.dto.user.request.RequestCreditCard;

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

        builder.requestCreditCard.setNumber("5181652903041943");
        builder.requestCreditCard.setHolderName("Jetosvaldo");
        builder.requestCreditCard.setBrand("MASTERCARD");
        builder.requestCreditCard.setSecurityCode("613");
        builder.requestCreditCard.setMonthExpiration("8");
        builder.requestCreditCard.setYearExpiration(yearNowString);

        return builder;
    }

    public RequestCreditCardBuilder withClientCreditCardNumber(String clientCreditCardNumber) {
        this.requestCreditCard.setNumber(clientCreditCardNumber);
        return this;
    }

    public RequestCreditCardBuilder withClientCreditCardHolderName(String clientCreditCardHolderName) {
        this.requestCreditCard.setHolderName(clientCreditCardHolderName);
        return this;
    }

    public RequestCreditCardBuilder withClientCreditCardBrand(String clientCreditCardBrand) {
        this.requestCreditCard.setBrand(clientCreditCardBrand);
        return this;
    }

    public RequestCreditCardBuilder withClientCreditCardSecurityCode(String clientCreditCardSecurityCode) {
        this.requestCreditCard.setSecurityCode(clientCreditCardSecurityCode);
        return this;
    }

    public RequestCreditCardBuilder withClientCreditCardMonthExpiration(String clientCreditCardMonthExpiration) {
        this.requestCreditCard.setMonthExpiration(clientCreditCardMonthExpiration);
        return this;
    }

    public RequestCreditCardBuilder withClientCreditCardYearExpiration(String clientCreditCardYearExpiration) {
        this.requestCreditCard.setYearExpiration(clientCreditCardYearExpiration);
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
