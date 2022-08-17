package br.com.compass.filmes.cliente.dto.response;

import br.com.compass.filmes.cliente.entities.enums.CreditCardBrandEnum;
import lombok.Data;

@Data
public class CreditCardResponse {

    private CreditCardBrandEnum clientCreditCardBrand;
    private String clientCreditCardHolderName;
}
