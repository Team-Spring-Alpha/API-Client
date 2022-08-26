package br.com.compass.filmes.cliente.util;

import br.com.compass.filmes.cliente.builders.RequestCreditCardBuilder;
import br.com.compass.filmes.cliente.dto.client.request.RequestCreditCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

class ValidRequestCreditCardTest {

    private ValidRequestCreditCard validRequestCreditCard;

    @BeforeEach
    void setUp() {
        this.validRequestCreditCard = new ValidRequestCreditCard();
    }

    @Test
    @DisplayName("should pass when inform a correct request credit card")
    void shouldPassWhenInformACorrectRequestCreditCard() {
        RequestCreditCard requestCreditCard = RequestCreditCardBuilder.one().now();
        this.validRequestCreditCard.validRequestCreditCard(requestCreditCard);
    }

    @Test
    @DisplayName("should not pass when inform a wrong credit card brand")
    void shouldNotPassWhenInformAWrongCreditCardBrand() {
        RequestCreditCard requestCreditCard = RequestCreditCardBuilder
                .one()
                .withClientCreditCardBrand("test")
                .now();
        Assertions.assertThrows(ResponseStatusException.class, () -> this.validRequestCreditCard.validRequestCreditCard(requestCreditCard));
    }

    @Test
    @DisplayName("should not pass when inform a wrong credit card security code")
    void shouldNotPassWhenInformAWrongCreditCardSecurityCode() {
        RequestCreditCard requestCreditCard = RequestCreditCardBuilder
                .one()
                .withClientCreditCardSecurityCode("0x0")
                .now();
        Assertions.assertThrows(ResponseStatusException.class, () -> this.validRequestCreditCard.validRequestCreditCard(requestCreditCard));
    }

    @Test
    @DisplayName("should not pass when inform a wrong credit card month expiration")
    void shouldNotPassWhenInformAWrongCreditCardMonthExpiration() {
        RequestCreditCard requestCreditCard = RequestCreditCardBuilder
                .one()
                .withClientCreditCardMonthExpiration("01")
                .now();
        Assertions.assertThrows(ResponseStatusException.class, () -> this.validRequestCreditCard.validRequestCreditCard(requestCreditCard));
    }

    @Test
    @DisplayName("should not pass when inform a wrong credit card year expiration")
    void shouldNotPassWhenInformAWrongCreditCardYearExpiration() {
        String dateString = LocalDate.now().plusYears(6).toString();
        RequestCreditCard requestCreditCard = RequestCreditCardBuilder
                .one()
                .withClientCreditCardYearExpiration(dateString)
                .now();
        Assertions.assertThrows(ResponseStatusException.class, () -> this.validRequestCreditCard.validRequestCreditCard(requestCreditCard));
    }

}