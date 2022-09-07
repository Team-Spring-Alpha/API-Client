package br.com.compass.filmes.cliente.entities;

import br.com.compass.filmes.cliente.enums.GenresEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "Client")
public class UserEntity {
    @Id
    private String id;
    @Field("clientName")
    private String name;
    @Field("clientCpf")
    private String cpf;
    @Field("clientEmail")
    private String email;
    @Field("clientPassword")
    private String password;
    @Field("clientIsBlocked")
    private boolean clientIsBlocked = false;
    @Field("clientBirthDate")
    private LocalDate birthDate;
    @Field("creditCards")
    private List<CreditCardEntity> cards;
    @Field("clientCategory")
    private List<GenresEnum> categories;
}
