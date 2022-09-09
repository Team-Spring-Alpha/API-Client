package br.com.compass.filmes.user.util;

import br.com.compass.filmes.user.builders.RequestRentOrBuyBuilder;
import br.com.compass.filmes.user.dto.moviepayment.RequestMoviePaymentDTO;
import br.com.compass.filmes.user.dto.moviepayment.RequestRentOrBuyDTO;
import br.com.compass.filmes.user.exceptions.RentAndBuyMoviesEmptyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ValidateRequestMoviePaymentUtilTest {

    private ValidateRequestMoviePaymentUtil validateRequestMoviePaymentUtil;
    private RequestMoviePaymentDTO requestMoviePaymentDTO;

    @BeforeEach
    void setUp() {
        this.validateRequestMoviePaymentUtil = new ValidateRequestMoviePaymentUtil();
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
                () -> validateRequestMoviePaymentUtil.validRequestMoviePayment(requestMoviePaymentDTO));
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

        validateRequestMoviePaymentUtil.validRequestMoviePayment(requestMoviePaymentDTO);
    }
}