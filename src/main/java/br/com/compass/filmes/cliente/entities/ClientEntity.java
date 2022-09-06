package br.com.compass.filmes.cliente.entities;

import br.com.compass.filmes.cliente.enums.ClientCategoryEnum;
import lombok.*;
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
@NoArgsConstructor
@Document(collection = "Client")
public class ClientEntity implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String clientName;
    private String clientCpf;
    private String clientEmail;
    private String clientPassword;
    private boolean clientIsBlocked = false;
    private LocalDate clientBirthDate;
    private List<CreditCardEntity> creditCards;
    private List<ClientCategoryEnum> clientCategory;

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
        return this.clientPassword;
    }

    @Override
    public String getUsername() {
        return this.clientEmail;
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
