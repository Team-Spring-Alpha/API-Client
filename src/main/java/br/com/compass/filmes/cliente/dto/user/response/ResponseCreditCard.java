package br.com.compass.filmes.cliente.dto.user.response;

import br.com.compass.filmes.cliente.enums.ClientCreditCardBrandEnum;
import lombok.Data;

@Data
public class ResponseCreditCard {
    private ClientCreditCardBrandEnum brand;
}
