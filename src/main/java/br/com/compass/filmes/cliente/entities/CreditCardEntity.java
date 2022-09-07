package br.com.compass.filmes.cliente.entities;

import br.com.compass.filmes.cliente.enums.ClientCreditCardBrandEnum;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "CreditCard")
public class CreditCardEntity {
    @Field("clientCreditCardBrand")
    private ClientCreditCardBrandEnum brand;
    @Field("clientCreditCardNumber")
    private String number;
    @Field("clientCreditSecurityCode")
    private String securityCode;
    @Field("clientCreditCardYearExpiration")
    private String yearExpiration;
    @Field("clientCreditCardMonthExpiration")
    private String monthExpiration;
    @Field("clientCreditCardHolderName")
    private String holderName;
}
