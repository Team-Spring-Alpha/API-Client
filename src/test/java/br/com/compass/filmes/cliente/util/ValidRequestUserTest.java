package br.com.compass.filmes.cliente.util;

import br.com.compass.filmes.cliente.builders.RequestUserBuilder;
import br.com.compass.filmes.cliente.dto.user.request.RequestUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

class ValidRequestUserTest {

    private ValidRequestUser validRequestUser;

    @BeforeEach
    private void setUp() {
        this.validRequestUser = new ValidRequestUser();
    }

    @Test
    @DisplayName("should pass the valid when inform a valid client categories")
    void shouldPassTheValidWhenInformAValidClientCategories() {
        RequestUser requestUser = RequestUserBuilder.one().now();
        this.validRequestUser.validRequestClient(requestUser);
    }

    @Test
    @DisplayName("shouldnt pass the valid when inform a wrong client categories")
    void shouldntPassTheValidWhenInformAWrongClientCategories() {
        List<String> clientCategories = new ArrayList<>();
        clientCategories.add("test");
        RequestUser requestUser = RequestUserBuilder.one()
                .withClientCategory(clientCategories)
                .now();
        Assertions.assertThrows(ResponseStatusException.class, () -> this.validRequestUser.validRequestClient(requestUser));
    }


}