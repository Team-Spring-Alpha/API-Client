package br.com.compass.filmes.user.builders;

import br.com.compass.filmes.user.dto.user.request.RequestCreditCardDTO;
import br.com.compass.filmes.user.entities.CreditCardEntity;
import br.com.compass.filmes.user.enums.CreditCardBrandEnum;

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

        builder.creditCardEntity.setHolderName("Jetosvaldo");
        builder.creditCardEntity.setNumber("5181652903041943");
        builder.creditCardEntity.setSecurityCode("613");
        builder.creditCardEntity.setBrand(CreditCardBrandEnum.MASTERCARD);
        builder.creditCardEntity.setMonthExpiration("8");
        builder.creditCardEntity.setYearExpiration(yearNowString);

        return builder;
    }

    public CreditCardEntityBuilder withCreditCardHolderName(String creditCardHolderName) {
        this.creditCardEntity.setHolderName(creditCardHolderName);
        return this;
    }

    public CreditCardEntityBuilder withCreditCardNumber(String creditCardNumber) {
        this.creditCardEntity.setNumber(creditCardNumber);
        return this;
    }

    public CreditCardEntityBuilder withCreditCardSecurityCode(String creditCardSecurityCode) {
        this.creditCardEntity.setSecurityCode(creditCardSecurityCode);
        return this;
    }

    public CreditCardEntityBuilder withCreditCardBrand(CreditCardBrandEnum creditCardBrand) {
        this.creditCardEntity.setBrand(creditCardBrand);
        return this;
    }

    public CreditCardEntityBuilder withCreditCardMonthExpiration(String creditCardMonthExpiration) {
        this.creditCardEntity.setMonthExpiration(creditCardMonthExpiration);
        return this;
    }

    public CreditCardEntityBuilder withCreditCardYearExpiration(String creditCardYearExpiration) {
        this.creditCardEntity.setYearExpiration(creditCardYearExpiration);
        return this;
    }

    public CreditCardEntityBuilder withRequestCreditCard(RequestCreditCardDTO requestCreditCardDTO) {
        this.creditCardEntity.setHolderName(requestCreditCardDTO.getHolderName());
        this.creditCardEntity.setNumber(requestCreditCardDTO.getNumber());
        this.creditCardEntity.setSecurityCode(requestCreditCardDTO.getSecurityCode());
        this.creditCardEntity.setMonthExpiration(requestCreditCardDTO.getMonthExpiration());
        this.creditCardEntity.setYearExpiration(requestCreditCardDTO.getYearExpiration());
        this.creditCardEntity.setBrand(CreditCardBrandEnum.valueOf(requestCreditCardDTO.getBrand()));

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
