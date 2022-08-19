package br.com.compass.filmes.cliente.dto.client.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
public class RequestClient {

    @NotBlank
    @Email
    private String clientEmail;
    @NotBlank
    @Pattern(regexp = "^[A-Z][A-Za-z ]*$", message = "Only letter should be used. And should be capitalized")
    private String clientName;
    @NotBlank
    private String clientPassword;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate clientBirthDate;
    @CPF
    @NotNull
    private String clientCpf;
    @Size(min = 1)
    private List<@Valid RequestCreditCard> creditCards;
    @NotNull
    private List<String> clientCategory;
}
