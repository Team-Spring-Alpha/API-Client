package br.com.compass.filmes.cliente.dto.client.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ResponseClient {
    private String id;
    private String clientName;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate clientBirthDate;
    private boolean clientIsBlocked;
    private List<ResponseCreditCard> creditCards;
}
