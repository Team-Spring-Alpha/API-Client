package br.com.compass.filmes.user.entities;

import br.com.compass.filmes.user.enums.CreditCardBrandEnum;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class CreditCardEntity {
    @Field("userCreditCardBrand")
    private CreditCardBrandEnum brand;
    @Field("userCreditCardNumber")
    private String number;
    @Field("userCreditCardSecurityCode")
    private String securityCode;
    @Field("userCreditCardYearExpiration")
    private String yearExpiration;
    @Field("userCreditCardMonthExpiration")
    private String monthExpiration;
    @Field("userCreditCardHolderName")
    private String holderName;
}
