package br.com.compass.filmes.cliente.util;

import br.com.compass.filmes.cliente.builders.RequestRentOrBuyBuilder;
import br.com.compass.filmes.cliente.dto.apiMovieManager.RequestMoviePayment;
import br.com.compass.filmes.cliente.dto.apiMovieManager.RequestRentOrBuy;
import br.com.compass.filmes.cliente.exceptions.RentAndBuyMoviesEmptyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ValidRequestMoviePaymentTest {

    private ValidRequestMoviePayment validRequestMoviePayment;
    private RequestMoviePayment requestMoviePayment;

    @BeforeEach
    void setUp() {
        this.validRequestMoviePayment = new ValidRequestMoviePayment();
        this.requestMoviePayment = new RequestMoviePayment();
    }

    @Test
    @DisplayName("should throw rent and buy movies is empty when both list is null or empty")
    void shouldThrowRentAndBuyMoviesEmptyExceptionWhenBothListIsNullOrEmpty() {
        RequestRentOrBuy moviesBuyAndRent = RequestRentOrBuyBuilder.one()
                .withBuyList(null)
                .withRentList(new ArrayList<>())
                .now();

        requestMoviePayment.setMovies(moviesBuyAndRent);

        Assertions.assertThrows(RentAndBuyMoviesEmptyException.class,
                () -> validRequestMoviePayment.validRequestMoviePayment(requestMoviePayment));
    }

    @Test
    @DisplayName("should pass when either list is not empty or null")
    void shouldPassWhenEitherRentOrBuyListIsNotEmptyOrNull() {
        List<Long> buyList = new ArrayList<>();
        buyList.add(2L);

        RequestRentOrBuy moviesBuyAndRent = RequestRentOrBuyBuilder
                .one()
                .withRentList(null)
                .withBuyList(buyList)
                .now();

        requestMoviePayment.setMovies(moviesBuyAndRent);

        validRequestMoviePayment.validRequestMoviePayment(requestMoviePayment);
    }
}