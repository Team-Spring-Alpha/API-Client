package br.com.compass.filmes.cliente.dto.security;

import lombok.*;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Token {

    private String email;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    private String accessToken;
    private String refreshToken;
}
