package br.com.compass.filmes.cliente.util;

import br.com.compass.filmes.cliente.dto.client.request.RequestClient;
import br.com.compass.filmes.cliente.enums.ClientCategoryEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ValidRequestClient {

    public void validRequestClient(RequestClient requestClient) {
        validClientCategory(requestClient);
    }

    private void validClientCategory(RequestClient requestClient) {
        try {
            List<ClientCategoryEnum> categoriesList = requestClient.getClientCategory().stream().map(
                    ClientCategoryEnum::valueOf
            ).collect(Collectors.toList());
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
