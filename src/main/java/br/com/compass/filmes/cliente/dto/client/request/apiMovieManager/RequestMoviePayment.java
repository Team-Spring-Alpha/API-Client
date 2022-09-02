package br.com.compass.filmes.cliente.dto.client.request.apiMovieManager;

import br.com.compass.filmes.cliente.dto.client.response.ResponseRentOrBuy;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RequestMoviePayment {

    @NotNull
    private RequestRentOrBuy movies;
    @CreditCardNumber
    @NotBlank
    private String creditCardNumber;
}
