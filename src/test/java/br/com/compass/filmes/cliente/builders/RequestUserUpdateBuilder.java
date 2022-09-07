package br.com.compass.filmes.cliente.builders;

import br.com.compass.filmes.cliente.dto.user.request.RequestUserUpdate;

import java.time.LocalDate;

public class RequestUserUpdateBuilder {
    private RequestUserUpdate requestUserUpdate;

    public RequestUserUpdateBuilder() {
    }

    public static RequestUserUpdateBuilder one(){
        RequestUserUpdateBuilder builder = new RequestUserUpdateBuilder();
        builder.requestUserUpdate = new RequestUserUpdate();

        builder.requestUserUpdate.setEmail("yosope1626@uptade.com");
        builder.requestUserUpdate.setName("Jetosvaldo Update");
        builder.requestUserUpdate.setPassword("lala12");
        builder.requestUserUpdate.setBirthDate(LocalDate.now().plusYears(1));

        return builder;
    }

    public RequestUserUpdateBuilder withName(String name) {
        this.requestUserUpdate.setName(name);
        return this;
    }

    public RequestUserUpdateBuilder withEmail(String email) {
        this.requestUserUpdate.setEmail(email);
        return this;
    }

    public RequestUserUpdateBuilder withPassword(String password) {
        this.requestUserUpdate.setPassword(password);
        return this;
    }

    public RequestUserUpdateBuilder withBirthDate(LocalDate birthDate) {
        this.requestUserUpdate.setBirthDate(birthDate);
        return this;
    }


    public RequestUserUpdate now() {
        return this.requestUserUpdate;
    }
}
