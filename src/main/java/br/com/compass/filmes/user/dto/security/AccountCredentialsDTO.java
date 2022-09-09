package br.com.compass.filmes.user.dto.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountCredentialsDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
