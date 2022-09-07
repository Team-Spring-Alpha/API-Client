package br.com.compass.filmes.cliente.builders;


import br.com.compass.filmes.cliente.dto.user.request.RequestCreditCard;

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

    public RequestCreditCardBuilder withCreditCardNumber(String creditCardNumber) {
        this.requestCreditCard.setNumber(creditCardNumber);
        return this;
    }

    public RequestCreditCardBuilder withCreditCardHolderName(String creditCardHolderName) {
        this.requestCreditCard.setHolderName(creditCardHolderName);
        return this;
    }

    public RequestCreditCardBuilder withCreditCardBrand(String creditCardBrand) {
        this.requestCreditCard.setBrand(creditCardBrand);
        return this;
    }

    public RequestCreditCardBuilder withCreditCardSecurityCode(String creditCardSecurityCode) {
        this.requestCreditCard.setSecurityCode(creditCardSecurityCode);
        return this;
    }

    public RequestCreditCardBuilder withCreditCardMonthExpiration(String creditCardMonthExpiration) {
        this.requestCreditCard.setMonthExpiration(creditCardMonthExpiration);
        return this;
    }

    public RequestCreditCardBuilder withCreditCardYearExpiration(String creditCardYearExpiration) {
        this.requestCreditCard.setYearExpiration(creditCardYearExpiration);
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
