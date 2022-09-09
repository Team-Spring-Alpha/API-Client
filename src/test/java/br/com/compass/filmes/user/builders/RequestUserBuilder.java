package br.com.compass.filmes.user.builders;

import br.com.compass.filmes.user.dto.user.request.RequestCreditCardDTO;
import br.com.compass.filmes.user.dto.user.request.RequestUserDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RequestUserBuilder {

    private RequestUserDTO requestUserDTO;

    public static RequestUserBuilder one() {
        RequestUserBuilder builder = new RequestUserBuilder();
        builder.requestUserDTO = new RequestUserDTO();

        builder.requestUserDTO.setName("Jetosvaldo");
        builder.requestUserDTO.setCpf("686.751.800-12");
        builder.requestUserDTO.setEmail("yosope1626@wnpop.com");
        builder.requestUserDTO.setPassword("123");
        builder.requestUserDTO.setBirthDate(LocalDate.now());

        List<RequestCreditCardDTO> creditCardList = RequestCreditCardBuilder.one().list();
        builder.requestUserDTO.setCards(creditCardList);

        List<String> categoryList = new ArrayList<>();
        categoryList.add("ACAO");
        builder.requestUserDTO.setCategory(categoryList);

        return builder;
    }

    public RequestUserBuilder withName(String name) {
        this.requestUserDTO.setName(name);
        return this;
    }

    public RequestUserBuilder withCpf(String cpf) {
        this.requestUserDTO.setCpf(cpf);
        return this;
    }

    public RequestUserBuilder withEmail(String email) {
        this.requestUserDTO.setEmail(email);
        return this;
    }

    public RequestUserBuilder withPassword(String password) {
        this.requestUserDTO.setPassword(password);
        return this;
    }

    public RequestUserBuilder withBirthDate(LocalDate birthDate) {
        this.requestUserDTO.setBirthDate(birthDate);
        return this;
    }

    public RequestUserBuilder withCreditCards(List<RequestCreditCardDTO> creditCards) {
        this.requestUserDTO.setCards(creditCards);
        return this;
    }

    public RequestUserBuilder withCategory(List<String> clientCategory) {
        this.requestUserDTO.setCategory(clientCategory);
        return this;
    }

    public RequestUserDTO now() {
        return this.requestUserDTO;
    }
}
