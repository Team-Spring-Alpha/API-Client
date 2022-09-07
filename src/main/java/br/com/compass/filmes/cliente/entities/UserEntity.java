package br.com.compass.filmes.cliente.entities;

import br.com.compass.filmes.cliente.enums.GenresEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "User")
public class UserEntity {
    @Id
    private String id;
    @Field("userName")
    private String name;
    @Field("userCpf")
    private String cpf;
    @Field("userEmail")
    private String email;
    @Field("userPassword")
    private String password;
    @Field("userIsBlocked")
    private boolean clientIsBlocked = false;
    @Field("userBirthDate")
    private LocalDate birthDate;
    @Field("creditCards")
    private List<CreditCardEntity> cards;
    @Field("userCategories")
    private List<GenresEnum> categories;
}
