package br.com.compass.filmes.cliente.dto.client.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseClient {
    private String id;
    private String clientName;
    private LocalDateTime clientBirthDate;
    private boolean clientIsBlocked;
    private ResponseCreditCard creditCards;
}
