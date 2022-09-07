package br.com.compass.filmes.cliente.builders;


import br.com.compass.filmes.cliente.dto.user.request.RequestCreditCardDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RequestCreditCardBuilder {

    private RequestCreditCardDTO requestCreditCardDTO;

    public RequestCreditCardBuilder() {
    }

    public static RequestCreditCardBuilder one() {
        RequestCreditCardBuilder builder = new RequestCreditCardBuilder();
        builder.requestCreditCardDTO = new RequestCreditCardDTO();
        int yearNow = LocalDate.now().getYear();
        String yearNowString = Integer.toString(yearNow);

        builder.requestCreditCardDTO.setNumber("5181652903041943");
        builder.requestCreditCardDTO.setHolderName("Jetosvaldo");
        builder.requestCreditCardDTO.setBrand("MASTERCARD");
        builder.requestCreditCardDTO.setSecurityCode("613");
        builder.requestCreditCardDTO.setMonthExpiration("8");
        builder.requestCreditCardDTO.setYearExpiration(yearNowString);

        return builder;
    }

    public RequestCreditCardBuilder withCreditCardNumber(String creditCardNumber) {
        this.requestCreditCardDTO.setNumber(creditCardNumber);
        return this;
    }

    public RequestCreditCardBuilder withCreditCardHolderName(String creditCardHolderName) {
        this.requestCreditCardDTO.setHolderName(creditCardHolderName);
        return this;
    }

    public RequestCreditCardBuilder withCreditCardBrand(String creditCardBrand) {
        this.requestCreditCardDTO.setBrand(creditCardBrand);
        return this;
    }

    public RequestCreditCardBuilder withCreditCardSecurityCode(String creditCardSecurityCode) {
        this.requestCreditCardDTO.setSecurityCode(creditCardSecurityCode);
        return this;
    }

    public RequestCreditCardBuilder withCreditCardMonthExpiration(String creditCardMonthExpiration) {
        this.requestCreditCardDTO.setMonthExpiration(creditCardMonthExpiration);
        return this;
    }

    public RequestCreditCardBuilder withCreditCardYearExpiration(String creditCardYearExpiration) {
        this.requestCreditCardDTO.setYearExpiration(creditCardYearExpiration);
        return this;
    }

    public RequestCreditCardDTO now() {
        return this.requestCreditCardDTO;
    }

    public List<RequestCreditCardDTO> list() {
        List<RequestCreditCardDTO> creditCardList = new ArrayList<>();
        creditCardList.add(this.requestCreditCardDTO);
        creditCardList.add(this.requestCreditCardDTO);
        return creditCardList;
    }
}
