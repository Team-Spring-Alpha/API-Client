package br.com.compass.filmes.user.builders;

import br.com.compass.filmes.user.dto.user.request.RequestUserUpdateDTO;

import java.time.LocalDate;

public class RequestUserUpdateBuilder {
    private RequestUserUpdateDTO requestUserUpdateDTO;

    public RequestUserUpdateBuilder() {
    }

    public static RequestUserUpdateBuilder one(){
        RequestUserUpdateBuilder builder = new RequestUserUpdateBuilder();
        builder.requestUserUpdateDTO = new RequestUserUpdateDTO();

        builder.requestUserUpdateDTO.setEmail("yosope1626@uptade.com");
        builder.requestUserUpdateDTO.setName("Jetosvaldo Update");
        builder.requestUserUpdateDTO.setPassword("lala12");
        builder.requestUserUpdateDTO.setBirthDate(LocalDate.now().plusYears(1));

        return builder;
    }

    public RequestUserUpdateBuilder withName(String name) {
        this.requestUserUpdateDTO.setName(name);
        return this;
    }

    public RequestUserUpdateBuilder withEmail(String email) {
        this.requestUserUpdateDTO.setEmail(email);
        return this;
    }

    public RequestUserUpdateBuilder withPassword(String password) {
        this.requestUserUpdateDTO.setPassword(password);
        return this;
    }

    public RequestUserUpdateBuilder withBirthDate(LocalDate birthDate) {
        this.requestUserUpdateDTO.setBirthDate(birthDate);
        return this;
    }


    public RequestUserUpdateDTO now() {
        return this.requestUserUpdateDTO;
    }
}
