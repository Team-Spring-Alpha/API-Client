package br.com.compass.filmes.user.entities;

import br.com.compass.filmes.user.enums.GenresEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Document(collection = "User")
public class UserEntity implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    @Field("userName")
    private String name;
    @Field("userCpf")
    private String cpf;
    @Field("userEmail")
    @Indexed(unique = true)
    private String email;
    @Field("userPassword")
    private String password;
    @Field("userIsBlocked")
    private boolean isBlocked = false;
    @Field("userBirthDate")
    private LocalDate birthDate;
    @Field("creditCards")
    private List<CreditCardEntity> cards;
    @Field("userCategories")
    private List<GenresEnum> categories;

    @Field("permission")
    private List<Permission> permissions;

    /*
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


     */

    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();
        for (Permission permission : permissions) {
            roles.add(permission.getDescription());
        }
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
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
        return !this.isBlocked;
    }

}
