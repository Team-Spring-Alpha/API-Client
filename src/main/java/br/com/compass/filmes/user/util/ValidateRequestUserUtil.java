package br.com.compass.filmes.user.util;

import br.com.compass.filmes.user.dto.user.request.RequestUserDTO;
import br.com.compass.filmes.user.enums.UserCategoryEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ValidateRequestUserUtil {

    public void validRequestUser(RequestUserDTO requestUserDTO) {
        validCategory(requestUserDTO);
    }

    private void validCategory(RequestUserDTO requestUserDTO) {
        try {
            List<UserCategoryEnum> categoriesList = requestUserDTO.getCategory().stream().map(
                    UserCategoryEnum::valueOf
            ).collect(Collectors.toList());
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
