package br.com.compass.filmes.cliente.builders;

import br.com.compass.filmes.cliente.dto.client.request.RequestCreditCard;
import br.com.compass.filmes.cliente.entities.CreditCardEntity;
import br.com.compass.filmes.cliente.enums.ClientCreditCardBrandEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreditCardEntityBuilder {

    private CreditCardEntity creditCardEntity;

    public CreditCardEntityBuilder() {
    }

    public static CreditCardEntityBuilder one() {
        CreditCardEntityBuilder builder = new CreditCardEntityBuilder();
        builder.creditCardEntity = new CreditCardEntity();
        int yearNow = LocalDate.now().getYear();
        String yearNowString = Integer.toString(yearNow);

        builder.creditCardEntity.setClientCreditCardHolderName("Jetosvaldo");
        builder.creditCardEntity.setClientCreditCardNumber("5181652903041943");
        builder.creditCardEntity.setClientCreditSecurityCode("613");
        builder.creditCardEntity.setClientCreditCardBrand(ClientCreditCardBrandEnum.MASTER_CARD);
        builder.creditCardEntity.setClientCreditCardMonthExpiration("8");
        builder.creditCardEntity.setClientCreditCardYearExpiration(yearNowString);

        return builder;
    }

    public CreditCardEntityBuilder withCreditCardHolderName(String creditCardHolderName) {
        this.creditCardEntity.setClientCreditCardHolderName(creditCardHolderName);
        return this;
    }

    public CreditCardEntityBuilder withCreditCardNumber(String creditCardNumber) {
        this.creditCardEntity.setClientCreditCardNumber(creditCardNumber);
        return this;
    }

    public CreditCardEntityBuilder withCreditCardSecurityCode(String creditCardSecurityCode) {
        this.creditCardEntity.setClientCreditSecurityCode(creditCardSecurityCode);
        return this;
    }

    public CreditCardEntityBuilder withCreditCardBrand(ClientCreditCardBrandEnum creditCardBrand) {
        this.creditCardEntity.setClientCreditCardBrand(creditCardBrand);
        return this;
    }

    public CreditCardEntityBuilder withCreditCardMonthExpiration(String creditCardMonthExpiration) {
        this.creditCardEntity.setClientCreditCardMonthExpiration(creditCardMonthExpiration);
        return this;
    }

    public CreditCardEntityBuilder withCreditCardYearExpiration(String creditCardYearExpiration) {
        this.creditCardEntity.setClientCreditCardYearExpiration(creditCardYearExpiration);
        return this;
    }

    public CreditCardEntityBuilder withRequestCreditCard(RequestCreditCard requestCreditCard) {
        this.creditCardEntity.setClientCreditCardHolderName(requestCreditCard.getClientCreditCardHolderName());
        this.creditCardEntity.setClientCreditCardNumber(requestCreditCard.getClientCreditCardNumber());
        this.creditCardEntity.setClientCreditSecurityCode(requestCreditCard.getClientCreditCardSecurityCode());
        this.creditCardEntity.setClientCreditCardMonthExpiration(requestCreditCard.getClientCreditCardMonthExpiration());
        this.creditCardEntity.setClientCreditCardYearExpiration(requestCreditCard.getClientCreditCardYearExpiration());
        this.creditCardEntity.setClientCreditCardBrand(ClientCreditCardBrandEnum.valueOf(requestCreditCard.getClientCreditCardBrand()));

        return this;
    }



    public CreditCardEntity now() {
        return this.creditCardEntity;
    }

    public List<CreditCardEntity> list() {
        List<CreditCardEntity> creditCardList = new ArrayList<>();
        creditCardList.add(this.creditCardEntity);
        creditCardList.add(this.creditCardEntity);
        return creditCardList;
    }

}
