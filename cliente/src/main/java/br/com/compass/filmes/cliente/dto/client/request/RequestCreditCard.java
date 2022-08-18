package br.com.compass.filmes.cliente.dto.client.request;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class RequestCreditCard {
    @NotBlank
    private String clientCreditCardBrand;
    @NotBlank
    @CreditCardNumber
    private String clientCreditCardNumber;
    @NotBlank
    private String clientCreditCardSecurityCode;
    @NotBlank
    private String clientCreditCardYearExpiration;
    @NotBlank
    private String clientCreditCardMonthExpiration;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z]*$", message = "Only letters can be used.")
    private String clientCreditCardHolderName;
}
