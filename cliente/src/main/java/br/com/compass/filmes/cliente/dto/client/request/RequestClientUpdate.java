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
public class RequestClientUpdate {

    @Email
    private String clientEmail;
    @Pattern(regexp = "^[A-Z][A-Za-z ]*$", message = "Only letters should be used. And should be capitalized.")
    private String clientName;
    private String clientPassword;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate clientBirthDate;
}
