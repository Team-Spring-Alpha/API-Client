package br.com.compass.filmes.cliente.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Document(collection = "client")
public class ClientEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientName;
    private String clientCpf;
    private boolean clientIsBlocked;
    private String clientEmail;
    private String clientPassword;
    private LocalDate clientBirthDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CreditCardEntity> creditCards;
}
