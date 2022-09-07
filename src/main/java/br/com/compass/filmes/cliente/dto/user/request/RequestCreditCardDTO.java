package br.com.compass.filmes.cliente.dto.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("security_code")
    private String securityCode;
    @NotBlank
    @JsonProperty("year_expiration")
    private String yearExpiration;
    @NotBlank
    @JsonProperty("month_expiration")
    private String monthExpiration;
    @NotBlank
    @Pattern(regexp = "^[A-Z][A-Za-z ]*$", message = "Only letters can be used. And should be capitalized")
    @JsonProperty("holder_name")
    private String holderName;
}
