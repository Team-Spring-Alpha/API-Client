package br.com.compass.filmes.cliente.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "Client")
public class ClientEntity {
    @Id
    private String id;
    private String ClientName;
    private String ClientCpf;
    private String ClientEmail;
    private String ClientPassword;
    private boolean ClientIsBlocked = false;
    private LocalDateTime ClientBirthDate;
    private CreditCardEntity CreditCards;
}
