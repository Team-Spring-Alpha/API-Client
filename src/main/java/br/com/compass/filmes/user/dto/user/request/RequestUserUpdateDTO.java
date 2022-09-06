package br.com.compass.filmes.user.dto.user.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class RequestUserUpdateDTO {

    @Email
    private String email;
    @Pattern(regexp = "^[A-Z][A-Za-z ]*$", message = "Only letters should be used. And should be capitalized.")
    private String name;
    private String password;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
}
