package br.com.compass.filmes.cliente.dto.user.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class RequestUserUpdateDTO {

    @Email
    private String email;
    @Pattern(regexp = "^[A-Z][A-Za-z ]*$", message = "Only letters should be used. And should be capitalized.")
    private String name;
    private String password;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("birth_date")
    private LocalDate birthDate;
}
