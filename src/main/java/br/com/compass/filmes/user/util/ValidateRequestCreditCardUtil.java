package br.com.compass.filmes.user.util;

import br.com.compass.filmes.user.dto.user.request.RequestCreditCardDTO;
import br.com.compass.filmes.user.enums.ClientCreditCardBrandEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Component
public class ValidateRequestCreditCardUtil {

    public void validRequestCreditCard(RequestCreditCardDTO requestCreditCardDTO) {

        validRequestCreditCardSecurityCode(requestCreditCardDTO);
        validRequestCreditCardBrand(requestCreditCardDTO);
        validRequestCreditCardMonthExpiration(requestCreditCardDTO);
        validRequestCreditCardYearExpiration(requestCreditCardDTO);

    }

    private void validRequestCreditCardBrand(RequestCreditCardDTO requestCreditCardDTO) {
        try {
            ClientCreditCardBrandEnum.valueOf(requestCreditCardDTO.getBrand());
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    private void validRequestCreditCardSecurityCode(RequestCreditCardDTO requestCreditCardDTO) {
        String regexStringSecurityCode = "[0-9]{3}";

        if (!requestCreditCardDTO.getSecurityCode().matches(regexStringSecurityCode)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    private void validRequestCreditCardMonthExpiration(RequestCreditCardDTO requestCreditCardDTO) {
        String regexStringMonthExpiration = "^[1-9]{1}|^1[0-2]{1}";

        if (!requestCreditCardDTO.getMonthExpiration().matches(regexStringMonthExpiration)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    private void validRequestCreditCardYearExpiration(RequestCreditCardDTO requestCreditCardDTO) {
        String regexStringAnoExpiracao = "^[0-9]{4}";
        boolean yearCreditCardIsValid = false;

        if (requestCreditCardDTO.getYearExpiration().matches(regexStringAnoExpiracao)) {
            int yearNow = LocalDate.now().getYear();
            int requestYear = Integer.parseInt(requestCreditCardDTO.getYearExpiration());

            boolean requestYearIsAfterOrEqualYearNow = requestYear >= yearNow;
            boolean requestYearIsBeforeOrEqualYearNowMinusFive = requestYear <= (yearNow + 5);

            if (requestYearIsAfterOrEqualYearNow && requestYearIsBeforeOrEqualYearNowMinusFive) {
                yearCreditCardIsValid = true;
            }
        }

        if (!yearCreditCardIsValid) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
