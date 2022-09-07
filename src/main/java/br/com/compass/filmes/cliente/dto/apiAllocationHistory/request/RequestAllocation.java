package br.com.compass.filmes.cliente.dto.apiAllocationHistory.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class RequestAllocation {
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("card_number")
    private String cardNumber;
    private List<RequestAllocationMovie> movies;
    @JsonProperty("payment_status")
    private String paymentStatus;
}
