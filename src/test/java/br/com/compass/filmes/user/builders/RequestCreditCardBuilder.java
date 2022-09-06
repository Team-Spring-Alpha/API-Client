package br.com.compass.filmes.user.builders;


import br.com.compass.filmes.user.dto.user.request.RequestCreditCardDTO;

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

    public RequestCreditCardBuilder withClientCreditCardNumber(String clientCreditCardNumber) {
        this.requestCreditCardDTO.setNumber(clientCreditCardNumber);
        return this;
    }

    public RequestCreditCardBuilder withClientCreditCardHolderName(String clientCreditCardHolderName) {
        this.requestCreditCardDTO.setHolderName(clientCreditCardHolderName);
        return this;
    }

    public RequestCreditCardBuilder withClientCreditCardBrand(String clientCreditCardBrand) {
        this.requestCreditCardDTO.setBrand(clientCreditCardBrand);
        return this;
    }

    public RequestCreditCardBuilder withClientCreditCardSecurityCode(String clientCreditCardSecurityCode) {
        this.requestCreditCardDTO.setSecurityCode(clientCreditCardSecurityCode);
        return this;
    }

    public RequestCreditCardBuilder withClientCreditCardMonthExpiration(String clientCreditCardMonthExpiration) {
        this.requestCreditCardDTO.setMonthExpiration(clientCreditCardMonthExpiration);
        return this;
    }

    public RequestCreditCardBuilder withClientCreditCardYearExpiration(String clientCreditCardYearExpiration) {
        this.requestCreditCardDTO.setYearExpiration(clientCreditCardYearExpiration);
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
