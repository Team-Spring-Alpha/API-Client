package br.com.compass.filmes.cliente.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CreditCardRequest {

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
    @Pattern(regexp = "^[A-Za-z]*$", message = "Apenas letras devem ser usadas.")
    private String clientCreditCardHolderName;
}
