package br.com.compass.filmes.cliente.dto.request;

import br.com.compass.filmes.cliente.entities.CreditCardEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {

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
    private List<@Valid CreditCardRequest> creditCards;
}
