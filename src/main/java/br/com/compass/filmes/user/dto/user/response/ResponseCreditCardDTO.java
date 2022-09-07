package br.com.compass.filmes.user.dto.user.response;

import br.com.compass.filmes.user.enums.CreditCardBrandEnum;
import lombok.Data;

@Data
public class ResponseCreditCardDTO {
    private CreditCardBrandEnum brand;
}
