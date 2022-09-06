package br.com.compass.filmes.cliente.util;

import br.com.compass.filmes.cliente.dto.apiMovieManager.RequestMoviePayment;
import br.com.compass.filmes.cliente.exceptions.RentAndBuyMoviesEmptyException;
import org.springframework.stereotype.Component;

@Component
public class ValidRequestMoviePayment {

    public void validRequestMoviePayment(RequestMoviePayment requestMoviePayment) {
        boolean isBuyListInvalid = isBuyListEmptyOrNull(requestMoviePayment);
        boolean isRentListInvalid = isRentListEmptyOrNull(requestMoviePayment);

        if (isBuyListInvalid && isRentListInvalid) {
            throw new RentAndBuyMoviesEmptyException();
        }
    }

    private boolean isRentListEmptyOrNull(RequestMoviePayment requestMoviePayment) {
        return requestMoviePayment.getMovies().getRent() == null || requestMoviePayment.getMovies().getRent().isEmpty();
    }

    private boolean isBuyListEmptyOrNull(RequestMoviePayment requestMoviePayment) {
        return requestMoviePayment.getMovies().getBuy() == null || requestMoviePayment.getMovies().getBuy().isEmpty();
    }
}
