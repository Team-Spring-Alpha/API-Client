package br.com.compass.filmes.cliente.dto.apiPayment.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProcessPaymentDTO {
    @JsonProperty("authorization_code")
    private String authorizationCode;
    @JsonProperty("authorized_at")
    private String authorizedAt;
    @JsonProperty("reason_code")
    private String reasonCode;
    @JsonProperty("reason_message")
    private String reasonMessage;
}
