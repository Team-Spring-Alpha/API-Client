package br.com.compass.filmes.user.dto.user.response;

import br.com.compass.filmes.user.enums.ClientCreditCardBrandEnum;
import lombok.Data;

@Data
public class ResponseCreditCardDTO {
    private ClientCreditCardBrandEnum brand;
}
