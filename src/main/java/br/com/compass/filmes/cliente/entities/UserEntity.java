package br.com.compass.filmes.cliente.entities;

import br.com.compass.filmes.cliente.enums.UserCategoryEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Document(collection = "Client")
public class UserEntity implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private String cpf;
    private String email;
    private String password;
    private boolean clientIsBlocked = false;
    private LocalDate birthDate;
    private List<CreditCardEntity> cards;
    private List<UserCategoryEnum> categories;

    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();
        roles.add(getAuthorities().toString());
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority("ROLE_USER"));
        return list;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
