package br.com.compass.filmes.cliente.entities;

import br.com.compass.filmes.cliente.enums.ClientCategoryEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "Client")
public class ClientEntity {
    @Id
    private String id;
    private String clientName;
    private String clientCpf;
    private String clientEmail;
    private String clientPassword;
    private boolean clientIsBlocked = false;
    private LocalDate clientBirthDate;
    private List<CreditCardEntity> creditCards;
    private List<ClientCategoryEnum> clientCategory;
}
