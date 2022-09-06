package br.com.compass.filmes.user.dto.user.request;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class RequestCreditCardDTO {
    @NotBlank
    private String brand;
    @NotBlank
    @CreditCardNumber
    private String number;
    @NotBlank
    private String securityCode;
    @NotBlank
    private String yearExpiration;
    @NotBlank
    private String monthExpiration;
    @NotBlank
    @Pattern(regexp = "^[A-Z][A-Za-z ]*$", message = "Only letters can be used. And should be capitalized")
    private String holderName;
}
