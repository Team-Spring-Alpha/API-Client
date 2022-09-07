package br.com.compass.filmes.cliente.util;

import br.com.compass.filmes.cliente.builders.RequestRentOrBuyBuilder;
import br.com.compass.filmes.cliente.dto.apiMovieManager.RequestMoviePaymentDTO;
import br.com.compass.filmes.cliente.dto.apiMovieManager.RequestRentOrBuyDTO;
import br.com.compass.filmes.cliente.exceptions.RentAndBuyMoviesEmptyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ValidRequestMoviePaymentDTOTest {

    private ValidRequestMoviePayment validRequestMoviePayment;
    private RequestMoviePaymentDTO requestMoviePaymentDTO;

    @BeforeEach
    void setUp() {
        this.validRequestMoviePayment = new ValidRequestMoviePayment();
        this.requestMoviePaymentDTO = new RequestMoviePaymentDTO();
    }

    @Test
    @DisplayName("should throw rent and buy movies is empty when both list is null or empty")
    void shouldThrowRentAndBuyMoviesEmptyExceptionWhenBothListIsNullOrEmpty() {
        RequestRentOrBuyDTO moviesBuyAndRent = RequestRentOrBuyBuilder.one()
                .withBuyList(null)
                .withRentList(new ArrayList<>())
                .now();

        requestMoviePaymentDTO.setMovies(moviesBuyAndRent);

        Assertions.assertThrows(RentAndBuyMoviesEmptyException.class,
                () -> validRequestMoviePayment.validRequestMoviePayment(requestMoviePaymentDTO));
    }

    @Test
    @DisplayName("should pass when either list is not empty or null")
    void shouldPassWhenEitherRentOrBuyListIsNotEmptyOrNull() {
        List<Long> buyList = new ArrayList<>();
        buyList.add(2L);

        RequestRentOrBuyDTO moviesBuyAndRent = RequestRentOrBuyBuilder
                .one()
                .withRentList(null)
                .withBuyList(buyList)
                .now();

        requestMoviePaymentDTO.setMovies(moviesBuyAndRent);

        validRequestMoviePayment.validRequestMoviePayment(requestMoviePaymentDTO);
    }
}