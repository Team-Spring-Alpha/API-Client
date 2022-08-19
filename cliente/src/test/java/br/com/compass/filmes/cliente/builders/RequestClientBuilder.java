package br.com.compass.filmes.cliente.builders;

import br.com.compass.filmes.cliente.dto.client.request.RequestClient;
import br.com.compass.filmes.cliente.dto.client.request.RequestCreditCard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RequestClientBuilder {

    private RequestClient requestClient;

    public static RequestClientBuilder one() {
        RequestClientBuilder builder = new RequestClientBuilder();
        builder.requestClient = new RequestClient();

        builder.requestClient.setClientName("Jetosvaldo");
        builder.requestClient.setClientCpf("686.751.800-12");
        builder.requestClient.setClientEmail("yosope1626@wnpop.com");
        builder.requestClient.setClientPassword("123");
        builder.requestClient.setClientBirthDate(LocalDate.now());

        List<RequestCreditCard> creditCardList = RequestCreditCardBuilder.one().list();
        builder.requestClient.setCreditCards(creditCardList);

        List<String> categoryList = new ArrayList<>();
        categoryList.add("ACAO");
        builder.requestClient.setClientCategory(categoryList);

        return builder;
    }

    public RequestClientBuilder withName(String name) {
        this.requestClient.setClientName(name);
        return this;
    }

    public RequestClientBuilder withCpf(String cpf) {
        this.requestClient.setClientName(cpf);
        return this;
    }

    public RequestClientBuilder withEmail(String email) {
        this.requestClient.setClientName(email);
        return this;
    }

    public RequestClientBuilder withPassword(String password) {
        this.requestClient.setClientName(password);
        return this;
    }

    public RequestClientBuilder withBirthDate(LocalDate birthDate) {
        this.requestClient.setClientBirthDate(birthDate);
        return this;
    }

    public RequestClientBuilder withCreditCards(List<RequestCreditCard> creditCards) {
        this.requestClient.setCreditCards(creditCards);
        return this;
    }

    public RequestClientBuilder withClientCategory(List<String> clientCategory) {
        this.requestClient.setClientCategory(clientCategory);
        return this;
    }

    public RequestClient now() {
        return this.requestClient;
    }
}
