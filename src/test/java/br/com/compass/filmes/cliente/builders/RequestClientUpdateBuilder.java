package br.com.compass.filmes.cliente.builders;

import br.com.compass.filmes.cliente.dto.client.request.RequestClientUpdate;

import java.time.LocalDate;

public class RequestClientUpdateBuilder {
    private RequestClientUpdate requestClientUpdate;

    public RequestClientUpdateBuilder() {
    }

    public static RequestClientUpdateBuilder one(){
        RequestClientUpdateBuilder builder = new RequestClientUpdateBuilder();
        builder.requestClientUpdate = new RequestClientUpdate();

        builder.requestClientUpdate.setClientEmail("yosope1626@uptade.com");
        builder.requestClientUpdate.setClientName("Jetosvaldo Update");
        builder.requestClientUpdate.setClientPassword("lala12");
        builder.requestClientUpdate.setClientBirthDate(LocalDate.now().plusYears(1));

        return builder;
    }

    public RequestClientUpdateBuilder withName(String name) {
        this.requestClientUpdate.setClientName(name);
        return this;
    }

    public RequestClientUpdateBuilder withEmail(String email) {
        this.requestClientUpdate.setClientEmail(email);
        return this;
    }

    public RequestClientUpdateBuilder withPassword(String password) {
        this.requestClientUpdate.setClientPassword(password);
        return this;
    }

    public RequestClientUpdateBuilder withBirthDate(LocalDate birthDate) {
        this.requestClientUpdate.setClientBirthDate(birthDate);
        return this;
    }


    public RequestClientUpdate now() {
        return this.requestClientUpdate;
    }
}
