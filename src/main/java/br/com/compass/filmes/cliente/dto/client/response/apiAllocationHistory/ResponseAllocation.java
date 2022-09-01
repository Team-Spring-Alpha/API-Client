package br.com.compass.filmes.cliente.dto.client.response.apiAllocationHistory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponseAllocation {

    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("allocation_number")
    private String allocationNumber;
    @JsonProperty("card_number")
    private int cardNumber;
    private List<ResponseAllocationMovie> movies;
    @JsonProperty("payment_status")
    private String paymentStatus;
    @JsonProperty("allocation_date")
    private LocalDateTime allocationDate;
}
