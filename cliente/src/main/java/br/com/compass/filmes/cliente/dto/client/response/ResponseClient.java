package br.com.compass.filmes.cliente.dto.client.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseClient {
    private String id;
    private String clientName;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime clientBirthDate;
    private boolean clientIsBlocked;
    private ResponseCreditCard creditCards;
}
