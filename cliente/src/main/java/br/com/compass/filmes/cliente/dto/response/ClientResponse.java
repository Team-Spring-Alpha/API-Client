package br.com.compass.filmes.cliente.dto.response;

import br.com.compass.filmes.cliente.entities.CreditCardEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ClientResponse {

    private Long id;
    private String clientName;
    private String clientCpf;
    private boolean clientIsBlocked;
    private String clientEmail;
    private LocalDate clientBirthDate;
    private List<CreditCardEntity> creditCards;
}
