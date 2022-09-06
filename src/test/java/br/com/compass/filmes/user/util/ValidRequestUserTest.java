package br.com.compass.filmes.user.util;

import br.com.compass.filmes.user.builders.RequestUserBuilder;
import br.com.compass.filmes.user.dto.user.request.RequestUser;
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
    @DisplayName("should pass the valid when inform a valid user categories")
    void shouldPassTheValidWhenInformAValidUserCategories() {
        RequestUser requestUser = RequestUserBuilder.one().now();
        this.validRequestUser.validRequestUser(requestUser);
    }

    @Test
    @DisplayName("shouldnt pass the valid when inform a wrong user categories")
    void shouldntPassTheValidWhenInformAWrongUserCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("test");
        RequestUser requestUser = RequestUserBuilder.one()
                .withClientCategory(categories)
                .now();
        Assertions.assertThrows(ResponseStatusException.class, () -> this.validRequestUser.validRequestUser(requestUser));
    }


}