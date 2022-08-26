package br.com.compass.filmes.cliente.util;

import br.com.compass.filmes.cliente.dto.client.request.RequestCreditCard;
import br.com.compass.filmes.cliente.enums.ClientCreditCardBrandEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Component
public class ValidRequestCreditCard {

    public void validRequestCreditCard(RequestCreditCard requestCreditCard) {

        validRequestCreditCardSecurityCode(requestCreditCard);
        validRequestCreditCardBrand(requestCreditCard);
        validRequestCreditCardMonthExpiration(requestCreditCard);
        validRequestCreditCardYearExpiration(requestCreditCard);

    }

    private void validRequestCreditCardBrand(RequestCreditCard requestCreditCard) {
        try {
            ClientCreditCardBrandEnum.valueOf(requestCreditCard.getClientCreditCardBrand());
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    private void validRequestCreditCardSecurityCode(RequestCreditCard requestCreditCard) {
        String regexStringSecurityCode = "[0-9]{3}";

        if (!requestCreditCard.getClientCreditCardSecurityCode().matches(regexStringSecurityCode)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    private void validRequestCreditCardMonthExpiration(RequestCreditCard requestCreditCard) {
        String regexStringMonthExpiration = "^[1-9]{1}|^1[0-2]{1}";

        if (!requestCreditCard.getClientCreditCardMonthExpiration().matches(regexStringMonthExpiration)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    private void validRequestCreditCardYearExpiration(RequestCreditCard requestCreditCard) {
        String regexStringAnoExpiracao = "^[0-9]{4}";
        boolean yearCreditCardIsValid = false;

        if (requestCreditCard.getClientCreditCardYearExpiration().matches(regexStringAnoExpiracao)) {
            int yearNow = LocalDate.now().getYear();
            int requestYear = Integer.parseInt(requestCreditCard.getClientCreditCardYearExpiration());

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
