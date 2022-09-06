package br.com.compass.filmes.user.entities;

import br.com.compass.filmes.user.enums.UserCategoryEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "User")
public class UserEntity {
    @Id
    private String id;
    private String name;
    private String cpf;
    private String email;
    private String password;
    private boolean isBlocked = false;
    private LocalDate birthDate;
    private List<CreditCardEntity> cards;
    private List<UserCategoryEnum> categories;
}
