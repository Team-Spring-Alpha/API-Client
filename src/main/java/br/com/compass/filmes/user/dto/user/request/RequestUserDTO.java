package br.com.compass.filmes.user.dto.user.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
public class RequestUserDTO {

    @NotBlank
    @Email
    @ApiModelProperty(example = "cleiton@test.com")
    private String email;
    @NotBlank
    @Pattern(regexp = "^[A-Z][A-Za-z ]*$", message = "Only letters should be used. And should be capitalized")
    @ApiModelProperty(example = "Cleiton")
    private String name;
    @NotBlank
    @Length(min = 3)
    @ApiModelProperty(example = "1234")
    private String password;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("birth_date")
    @ApiModelProperty(example = "23/02/1978")
    private LocalDate birthDate;
    @CPF
    @NotNull
    @ApiModelProperty(example = "111.111.111-01")
    private String cpf;
    @Size(min = 1)
    private List<@Valid RequestCreditCardDTO> cards;
    @NotNull
    private List<String> category;
}
