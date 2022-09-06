package br.com.compass.filmes.cliente.builders;

import br.com.compass.filmes.cliente.dto.user.request.RequestUser;
import br.com.compass.filmes.cliente.dto.user.request.RequestCreditCard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RequestUserBuilder {

    private RequestUser requestUser;

    public static RequestUserBuilder one() {
        RequestUserBuilder builder = new RequestUserBuilder();
        builder.requestUser = new RequestUser();

        builder.requestUser.setName("Jetosvaldo");
        builder.requestUser.setCpf("686.751.800-12");
        builder.requestUser.setEmail("yosope1626@wnpop.com");
        builder.requestUser.setPassword("123");
        builder.requestUser.setBirthDate(LocalDate.now());

        List<RequestCreditCard> creditCardList = RequestCreditCardBuilder.one().list();
        builder.requestUser.setCards(creditCardList);

        List<String> categoryList = new ArrayList<>();
        categoryList.add("ACAO");
        builder.requestUser.setCategory(categoryList);

        return builder;
    }

    public RequestUserBuilder withName(String name) {
        this.requestUser.setName(name);
        return this;
    }

    public RequestUserBuilder withCpf(String cpf) {
        this.requestUser.setName(cpf);
        return this;
    }

    public RequestUserBuilder withEmail(String email) {
        this.requestUser.setName(email);
        return this;
    }

    public RequestUserBuilder withPassword(String password) {
        this.requestUser.setName(password);
        return this;
    }

    public RequestUserBuilder withBirthDate(LocalDate birthDate) {
        this.requestUser.setBirthDate(birthDate);
        return this;
    }

    public RequestUserBuilder withCreditCards(List<RequestCreditCard> creditCards) {
        this.requestUser.setCards(creditCards);
        return this;
    }

    public RequestUserBuilder withClientCategory(List<String> clientCategory) {
        this.requestUser.setCategory(clientCategory);
        return this;
    }

    public RequestUser now() {
        return this.requestUser;
    }
}
