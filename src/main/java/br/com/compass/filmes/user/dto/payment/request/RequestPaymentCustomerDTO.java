package br.com.compass.filmes.cliente.dto.payment.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestPaymentCustomerDTO {
    @JsonProperty("document_type")
    private final String documentType = "CPF";
    @JsonProperty("document_number")
    @NonNull
    private final String documentNumber;
}
