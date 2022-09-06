package br.com.compass.filmes.cliente.builders;

import br.com.compass.filmes.cliente.dto.user.request.RequestUserUpdate;

import java.time.LocalDate;

public class RequestClientUpdateBuilder {
    private RequestUserUpdate requestUserUpdate;

    public RequestClientUpdateBuilder() {
    }

    public static RequestClientUpdateBuilder one(){
        RequestClientUpdateBuilder builder = new RequestClientUpdateBuilder();
        builder.requestUserUpdate = new RequestUserUpdate();

        builder.requestUserUpdate.setEmail("yosope1626@uptade.com");
        builder.requestUserUpdate.setName("Jetosvaldo Update");
        builder.requestUserUpdate.setPassword("lala12");
        builder.requestUserUpdate.setBirthDate(LocalDate.now().plusYears(1));

        return builder;
    }

    public RequestClientUpdateBuilder withName(String name) {
        this.requestUserUpdate.setName(name);
        return this;
    }

    public RequestClientUpdateBuilder withEmail(String email) {
        this.requestUserUpdate.setEmail(email);
        return this;
    }

    public RequestClientUpdateBuilder withPassword(String password) {
        this.requestUserUpdate.setPassword(password);
        return this;
    }

    public RequestClientUpdateBuilder withBirthDate(LocalDate birthDate) {
        this.requestUserUpdate.setBirthDate(birthDate);
        return this;
    }


    public RequestUserUpdate now() {
        return this.requestUserUpdate;
    }
}
