package br.com.compass.filmes.cliente.dto.apiPayment.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestPaymentCustomer {
    @JsonProperty("document_type")
    private String documentType = "CPF";
    @JsonProperty("document_number")
    private String documentNumber;
}
