package br.com.compass.filmes.cliente.dto.apiPayment.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ResponseGatewayReprovedDTO {
    @JsonProperty("payment_status")
    private String paymentStatus = "REPROVED";
    private final String cause;

    public ResponseGatewayReprovedDTO(String cause) {
        this.cause = cause;
    }

    public ResponseGatewayReprovedDTO(String paymentStatus, String cause) {
        this.paymentStatus = paymentStatus;
        this.cause = cause;
    }
}
