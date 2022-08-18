package br.com.compass.filmes.cliente.entities;

import br.com.compass.filmes.cliente.enums.ClientCreditCardBrandEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "CreditCard")
public class CreditCardEntity {
    @Id
    private String id;
    private ClientCreditCardBrandEnum ClientCreditCardBrand;
    private String ClientCreditCardNumber;
    private String ClientCreditSecurityCode;
    private String ClientCreditCardYearExpiration;
    private String ClientCreditCardMonthExpiration;
    private String ClientCreditCardHolderName;
}
