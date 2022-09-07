package br.com.compass.filmes.cliente.entities;

import br.com.compass.filmes.cliente.enums.UserCategoryEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "Client")
public class UserEntity {
    @Id
    private String id;
    private String name;
    private String cpf;
    private String email;
    private String password;
    private boolean clientIsBlocked = false;
    private LocalDate birthDate;
    private List<CreditCardEntity> cards;
    private List<UserCategoryEnum> categories;
}
