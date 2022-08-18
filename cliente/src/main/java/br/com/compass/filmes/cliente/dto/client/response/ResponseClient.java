package br.com.compass.filmes.cliente.dto.client.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseClient {
    private String id;
    private String ClientName;
    private LocalDateTime ClientBirthDate;
    private boolean ClientIsBlocked;
    private ResponseCreditCard CreditCards;
}
