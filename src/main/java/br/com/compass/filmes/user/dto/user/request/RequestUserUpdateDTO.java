package br.com.compass.filmes.user.dto.user.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class RequestUserUpdateDTO {

    @Email
    @ApiModelProperty(example = "jetosvaldo@teste.com")
    private String email;
    @Pattern(regexp = "^[A-Z][A-Za-z ]*$", message = "Only letters should be used. And should be capitalized.")
    @ApiModelProperty(example = "Jetosvaldo")
    private String name;
    @ApiModelProperty(example = "123456789")
    private String password;
    @ApiModelProperty(example = "14/04/2001")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("birth_date")
    private LocalDate birthDate;
}
