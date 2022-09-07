package br.com.compass.filmes.user.util;

import br.com.compass.filmes.user.builders.RequestUserBuilder;
import br.com.compass.filmes.user.dto.user.request.RequestUserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

class ValidateRequestUserUtilTest {

    private ValidateRequestUserUtil validateRequestUserUtil;

    @BeforeEach
    private void setUp() {
        this.validateRequestUserUtil = new ValidateRequestUserUtil();
    }

    @Test
    @DisplayName("should pass the valid when inform a valid user categories")
    void shouldPassTheValidWhenInformAValidUserCategories() {
        RequestUserDTO requestUserDTO = RequestUserBuilder.one().now();
        this.validateRequestUserUtil.validRequestUser(requestUserDTO);
    }

    @Test
    @DisplayName("shouldnt pass the valid when inform a wrong user categories")
    void shouldntPassTheValidWhenInformAWrongUserCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("test");
        RequestUserDTO requestUserDTO = RequestUserBuilder.one()
                .withClientCategory(categories)
                .now();
        Assertions.assertThrows(ResponseStatusException.class, () -> this.validateRequestUserUtil.validRequestUser(requestUserDTO));
    }


}