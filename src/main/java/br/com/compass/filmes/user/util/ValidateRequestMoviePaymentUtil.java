package br.com.compass.filmes.user.util;

import br.com.compass.filmes.user.dto.moviepayment.RequestMoviePaymentDTO;
import br.com.compass.filmes.user.exceptions.RentAndBuyMoviesEmptyException;
import org.springframework.stereotype.Component;

@Component
public class ValidateRequestMoviePaymentUtil {

    public void validRequestMoviePayment(RequestMoviePaymentDTO requestMoviePaymentDTO) {
        boolean isBuyListInvalid = isBuyListEmptyOrNull(requestMoviePaymentDTO);
        boolean isRentListInvalid = isRentListEmptyOrNull(requestMoviePaymentDTO);

        if (isBuyListInvalid && isRentListInvalid) {
            throw new RentAndBuyMoviesEmptyException();
        }
    }

    private boolean isRentListEmptyOrNull(RequestMoviePaymentDTO requestMoviePaymentDTO) {
        return requestMoviePaymentDTO.getMoviesRent() == null || requestMoviePaymentDTO.getMoviesRent().isEmpty();
    }

    private boolean isBuyListEmptyOrNull(RequestMoviePaymentDTO requestMoviePaymentDTO) {
        return requestMoviePaymentDTO.getMoviesBuy() == null || requestMoviePaymentDTO.getMoviesBuy().isEmpty();
    }
}
