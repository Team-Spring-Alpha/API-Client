package br.com.compass.filmes.cliente.util;

import br.com.compass.filmes.cliente.dto.user.request.RequestUser;
import br.com.compass.filmes.cliente.enums.GenresEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ValidRequestUser {

    public void validRequestUser(RequestUser requestUser) {
        validCategory(requestUser);
    }

    private void validCategory(RequestUser requestUser) {
        try {
            List<GenresEnum> categoriesList = requestUser.getCategory().stream().map(
                    GenresEnum::valueOf
            ).collect(Collectors.toList());
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
