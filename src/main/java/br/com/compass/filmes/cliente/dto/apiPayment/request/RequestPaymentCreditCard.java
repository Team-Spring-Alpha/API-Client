package br.com.compass.filmes.cliente.dto.apiPayment.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestPaymentCreditCard {
    private String brand;
    @JsonProperty("number_token")
    private String number;
    @JsonProperty("security_code")
    private String securityCode;
    @JsonProperty("expiration_year")
    private String yearExpiration;
    @JsonProperty("expiration_month")
    private String monthExpiration;
    @JsonProperty("cardholder_name")
    private String holderName;
}
