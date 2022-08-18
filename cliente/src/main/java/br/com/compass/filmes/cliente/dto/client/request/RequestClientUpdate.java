package br.com.compass.filmes.cliente.dto.client.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestClientUpdate {

    @Email
    private String clientEmail;
    @Pattern(regexp = "^[A-Za-z]*$", message = "Apenas letras devem ser usadas.")
    private String clientName;
    private String clientPassword;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime clientBirthDate;
}
