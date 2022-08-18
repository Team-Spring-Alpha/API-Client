package br.com.compass.filmes.cliente.dto.client.request;

import br.com.compass.filmes.cliente.entities.CreditCardEntity;
import br.com.compass.filmes.cliente.enums.ClientCategoryEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class RequestClient {

    @NotBlank
    @Email
    private String clientEmail;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z]*$", message = "Apenas letras devem ser usadas.")
    private String clientName;
    @NotBlank
    private String clientPassword;
    @NotBlank
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate clientBirthDate;
    @CPF
    private String clientCpf;
    @NotNull
    @Size(min = 1)
    private List<@Valid RequestCreditCard> creditCards;
    @NotBlank
    private List<String> clientCategory;
}
