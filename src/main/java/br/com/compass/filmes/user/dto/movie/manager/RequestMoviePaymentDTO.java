package br.com.compass.filmes.cliente.dto.movie.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RequestMoviePaymentDTO {

    @NotNull
    private RequestRentOrBuyDTO movies;
    @CreditCardNumber
    @NotBlank
    @JsonProperty("credit_card_number")
    private String creditCardNumber;
    @NotBlank
    @JsonProperty("user_id")
    private String userId;
}
