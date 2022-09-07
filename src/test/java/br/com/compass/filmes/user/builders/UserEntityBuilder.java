package br.com.compass.filmes.user.builders;

import br.com.compass.filmes.user.dto.user.request.RequestUserDTO;
import br.com.compass.filmes.user.entities.CreditCardEntity;
import br.com.compass.filmes.user.entities.UserEntity;
import br.com.compass.filmes.user.enums.GenresEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserEntityBuilder {

    private UserEntity userEntity;

    public UserEntityBuilder() {
    }

    public static UserEntityBuilder one() {
        UserEntityBuilder builder = new UserEntityBuilder();
        builder.userEntity = new UserEntity();

        builder.userEntity.setId("1");
        builder.userEntity.setName("Jetosvaldo");
        builder.userEntity.setCpf("686.751.800-12");
        builder.userEntity.setEmail("yosope1626@wnpop.com");
        builder.userEntity.setPassword("123");
        builder.userEntity.setBlocked(false);
        builder.userEntity.setBirthDate(LocalDate.now());

        List<GenresEnum> categoryEnumList = new ArrayList<>();
        categoryEnumList.add(GenresEnum.ACAO);
        builder.userEntity.setCategories(categoryEnumList);

        List<CreditCardEntity> creditCardEntityList = CreditCardEntityBuilder.one().list();
        builder.userEntity.setCards(creditCardEntityList);

        return builder;
    }

    public UserEntityBuilder withId(String id) {
        this.userEntity.setId(id);
        return this;
    }

    public UserEntityBuilder withName(String name) {
        this.userEntity.setName(name);
        return this;
    }

    public UserEntityBuilder withCpf(String cpf) {
        this.userEntity.setCpf(cpf);
        return this;
    }

    public UserEntityBuilder withEmail(String email) {
        this.userEntity.setEmail(email);
        return this;
    }

    public UserEntityBuilder withPassword(String password) {
        this.userEntity.setPassword(password);
        return this;
    }

    public UserEntityBuilder withIsBlocked(boolean isBlocked) {
        this.userEntity.setBlocked(isBlocked);
        return this;
    }

    public UserEntityBuilder withBirthDate(LocalDate birthDate) {
        this.userEntity.setBirthDate(birthDate);
        return this;
    }

    public UserEntityBuilder withCategory(List<GenresEnum> categoryEnumList) {
        this.userEntity.setCategories(categoryEnumList);
        return this;
    }

    public UserEntityBuilder withCreditCard(List<CreditCardEntity> creditCardEntity) {
        this.userEntity.setCards(creditCardEntity);
        return this;
    }

    public UserEntityBuilder withRequestUser(RequestUserDTO requestUserDTO) {
        this.userEntity.setId("idTeste");
        this.userEntity.setName(requestUserDTO.getName());
        this.userEntity.setCpf(requestUserDTO.getCpf());
        this.userEntity.setEmail(requestUserDTO.getEmail());
        this.userEntity.setPassword(requestUserDTO.getPassword());
        this.userEntity.setBlocked(false);
        this.userEntity.setBirthDate(requestUserDTO.getBirthDate());

        this.userEntity.setCategories(requestUserDTO.getCategory().stream().map(
                GenresEnum::valueOf
        ).collect(Collectors.toList()));


        this.userEntity.setCards(requestUserDTO.getCards().stream().map(
                requestCreditCard -> CreditCardEntityBuilder.one().withRequestCreditCard(requestCreditCard).now()
                ).collect(Collectors.toList()));

        return this;
    }

    public UserEntity now() {
        return this.userEntity;
    }

}
