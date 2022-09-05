package br.com.compass.filmes.cliente.dto.security;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountCredentials {
    private String email;
    private String password;
}
