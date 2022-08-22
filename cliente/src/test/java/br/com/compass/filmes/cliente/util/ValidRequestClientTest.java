package br.com.compass.filmes.cliente.util;

import br.com.compass.filmes.cliente.builders.RequestClientBuilder;
import br.com.compass.filmes.cliente.dto.client.request.RequestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

class ValidRequestClientTest {

    private ValidRequestClient validRequestClient;

    @BeforeEach
    private void setUp() {
        this.validRequestClient = new ValidRequestClient();
    }

    @Test
    @DisplayName("should pass the valid when inform a valid client categories")
    void shouldPassTheValidWhenInformAValidClientCategories() {
        RequestClient requestClient = RequestClientBuilder.one().now();
        this.validRequestClient.validRequestClient(requestClient);
    }

    @Test
    @DisplayName("shouldnt pass the valid when inform a wrong client categories")
    void shouldntPassTheValidWhenInformAWrongClientCategories() {
        List<String> clientCategories = new ArrayList<>();
        clientCategories.add("test");
        RequestClient requestClient = RequestClientBuilder.one()
                .withClientCategory(clientCategories)
                .now();
        Assertions.assertThrows(ResponseStatusException.class, () -> this.validRequestClient.validRequestClient(requestClient));
    }


}