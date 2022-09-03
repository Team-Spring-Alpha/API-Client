package br.com.compass.filmes.cliente.dto.apiPayment.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestPaymentCreditCard {
    @JsonProperty("brand")
    private String clientCreditCardBrand;
    @JsonProperty("number_token")
    private String clientCreditCardNumber;
    @JsonProperty("security_code")
    private String clientCreditCardSecurityCode;
    @JsonProperty("expiration_year")
    private String clientCreditCardYearExpiration;
    @JsonProperty("expiration_month")
    private String clientCreditCardMonthExpiration;
    @JsonProperty("cardholder_name")
    private String clientCreditCardHolderName;
}
