package br.com.compass.filmes.user.util;

import br.com.compass.filmes.user.builders.RequestCreditCardBuilder;
import br.com.compass.filmes.user.dto.user.request.RequestCreditCardDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

class ValidateRequestCreditCardUtilTest {

    private ValidateRequestCreditCardUtil validateRequestCreditCardUtil;

    @BeforeEach
    void setUp() {
        this.validateRequestCreditCardUtil = new ValidateRequestCreditCardUtil();
    }

    @Test
    @DisplayName("should pass when inform a correct request credit card")
    void shouldPassWhenInformACorrectRequestCreditCard() {
        RequestCreditCardDTO requestCreditCardDTO = RequestCreditCardBuilder.one().now();
        this.validateRequestCreditCardUtil.validRequestCreditCard(requestCreditCardDTO);
    }

    @Test
    @DisplayName("should not pass when inform a wrong credit card brand")
    void shouldNotPassWhenInformAWrongCreditCardBrand() {
        RequestCreditCardDTO requestCreditCardDTO = RequestCreditCardBuilder
                .one()
                .withCreditCardBrand("test")
                .now();
        Assertions.assertThrows(ResponseStatusException.class, () -> this.validateRequestCreditCardUtil.validRequestCreditCard(requestCreditCardDTO));
    }

    @Test
    @DisplayName("should not pass when inform a wrong credit card security code")
    void shouldNotPassWhenInformAWrongCreditCardSecurityCode() {
        RequestCreditCardDTO requestCreditCardDTO = RequestCreditCardBuilder
                .one()
                .withCreditCardSecurityCode("0x0")
                .now();
        Assertions.assertThrows(ResponseStatusException.class, () -> this.validateRequestCreditCardUtil.validRequestCreditCard(requestCreditCardDTO));
    }

    @Test
    @DisplayName("should not pass when inform a wrong credit card month expiration")
    void shouldNotPassWhenInformAWrongCreditCardMonthExpiration() {
        RequestCreditCardDTO requestCreditCardDTO = RequestCreditCardBuilder
                .one()
                .withCreditCardMonthExpiration("01")
                .now();
        Assertions.assertThrows(ResponseStatusException.class, () -> this.validateRequestCreditCardUtil.validRequestCreditCard(requestCreditCardDTO));
    }

    @Test
    @DisplayName("should not pass when inform a wrong credit card year expiration")
    void shouldNotPassWhenInformAWrongCreditCardYearExpiration() {
        String dateString = LocalDate.now().plusYears(6).toString();
        RequestCreditCardDTO requestCreditCardDTO = RequestCreditCardBuilder
                .one()
                .withCreditCardYearExpiration(dateString)
                .now();
        Assertions.assertThrows(ResponseStatusException.class, () -> this.validateRequestCreditCardUtil.validRequestCreditCard(requestCreditCardDTO));
    }

}