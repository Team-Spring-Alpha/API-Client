package br.com.compass.filmes.user.dto.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountCredentials {
    private String email;
    private String password;
}
