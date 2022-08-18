package br.com.compass.filmes.cliente.entities;

import br.com.compass.filmes.cliente.enums.ClientCategoryEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

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
    private LocalDateTime clientBirthDate;
    private CreditCardEntity creditCards;
    private Set<ClientCategoryEnum> clientCategory;
}
