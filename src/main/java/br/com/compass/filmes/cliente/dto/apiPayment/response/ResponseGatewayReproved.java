package br.com.compass.filmes.cliente.dto.apiPayment.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseGatewayReproved {
    @JsonProperty("payment_status")
    private String paymentStatus = "REPROVED";
    private String cause;
}
