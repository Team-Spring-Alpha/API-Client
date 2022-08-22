package br.com.compass.filmes.cliente.entities;

import br.com.compass.filmes.cliente.enums.ClientCreditCardBrandEnum;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "CreditCard")
public class CreditCardEntity {
    private ClientCreditCardBrandEnum clientCreditCardBrand;
    private String clientCreditCardNumber;
    private String clientCreditSecurityCode;
    private String clientCreditCardYearExpiration;
    private String clientCreditCardMonthExpiration;
    private String clientCreditCardHolderName;
}
