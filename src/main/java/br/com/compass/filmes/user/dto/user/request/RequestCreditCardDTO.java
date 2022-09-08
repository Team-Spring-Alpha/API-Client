package br.com.compass.filmes.user.dto.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class RequestCreditCardDTO {
    @NotBlank
    @ApiModelProperty(example = "VISA")
    private String brand;
    @NotBlank
    @CreditCardNumber
    @ApiModelProperty(example = "5331841082380540")
    private String number;
    @NotBlank
    @JsonProperty("security_code")
    @ApiModelProperty(example = "123")
    private String securityCode;
    @NotBlank
    @JsonProperty("year_expiration")
    @ApiModelProperty(example = "2026")
    private String yearExpiration;
    @NotBlank
    @JsonProperty("month_expiration")
    @ApiModelProperty(example = "1")
    private String monthExpiration;
    @NotBlank
    @Pattern(regexp = "^[A-Z][A-Za-z ]*$", message = "Only letters can be used. And should be capitalized")
    @JsonProperty("holder_name")
    @ApiModelProperty(example = "Cleiton")
    private String holderName;
}
