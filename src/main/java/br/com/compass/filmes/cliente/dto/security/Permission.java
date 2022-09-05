package br.com.compass.filmes.cliente.dto.security;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Permission implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String description;

    //Inserir os papeis /admin /user /moderador

    @Override
    public String getAuthority() {
        return this.description;
    }

}
