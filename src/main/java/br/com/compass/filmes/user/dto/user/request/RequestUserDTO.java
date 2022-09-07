package br.com.compass.filmes.user.dto.user.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
public class RequestUserDTO {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Pattern(regexp = "^[A-Z][A-Za-z ]*$", message = "Only letters should be used. And should be capitalized")
    private String name;
    @NotBlank
    private String password;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("birth_date")
    private LocalDate birthDate;
    @CPF
    @NotNull
    private String cpf;
    @Size(min = 1)
    private List<@Valid RequestCreditCardDTO> cards;
    @NotNull
    private List<String> category;
}
