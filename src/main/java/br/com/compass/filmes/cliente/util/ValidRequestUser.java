package br.com.compass.filmes.cliente.util;

import br.com.compass.filmes.cliente.dto.user.request.RequestUser;
import br.com.compass.filmes.cliente.enums.UserCategoryEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ValidRequestUser {

    public void validRequestClient(RequestUser requestUser) {
        validClientCategory(requestUser);
    }

    private void validClientCategory(RequestUser requestUser) {
        try {
            List<UserCategoryEnum> categoriesList = requestUser.getCategory().stream().map(
                    UserCategoryEnum::valueOf
            ).collect(Collectors.toList());
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
