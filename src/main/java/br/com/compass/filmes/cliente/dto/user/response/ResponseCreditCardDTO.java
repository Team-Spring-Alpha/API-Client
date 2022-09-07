package br.com.compass.filmes.cliente.dto.user.response;

import br.com.compass.filmes.cliente.enums.CreditCardBrandEnum;
import lombok.Data;

@Data
public class ResponseCreditCardDTO {
    private CreditCardBrandEnum brand;
}
