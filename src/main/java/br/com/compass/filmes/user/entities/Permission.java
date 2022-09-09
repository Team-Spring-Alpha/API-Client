package br.com.compass.filmes.user.entities;

import br.com.compass.filmes.user.enums.GenresEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode
@Data
public class Permission implements Serializable, GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Field("roles")
    private String description = "ROLE_USER";

    @Override
    public String getAuthority() {
        return this.description;
    }
}
