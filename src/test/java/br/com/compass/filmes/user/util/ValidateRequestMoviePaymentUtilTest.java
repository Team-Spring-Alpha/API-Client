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
        requestMoviePaymentDTO.setMoviesBuy(null);
        requestMoviePaymentDTO.setMoviesRent(new ArrayList<>());

        Assertions.assertThrows(RentAndBuyMoviesEmptyException.class,
                () -> validateRequestMoviePaymentUtil.validRequestMoviePayment(requestMoviePaymentDTO));
    }

    @Test
    @DisplayName("should pass when either list is not empty or null")
    void shouldPassWhenEitherRentOrBuyListIsNotEmptyOrNull() {
        RequestRentOrBuyDTO buyDTO = RequestRentOrBuyBuilder.one().now();
        List<RequestRentOrBuyDTO> buyList = new ArrayList<>();
        buyList.add(buyDTO);

        requestMoviePaymentDTO.setMoviesBuy(buyList);

        validateRequestMoviePaymentUtil.validRequestMoviePayment(requestMoviePaymentDTO);
    }
}