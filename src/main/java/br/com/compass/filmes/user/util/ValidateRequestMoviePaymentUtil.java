package br.com.compass.filmes.cliente.util;

import br.com.compass.filmes.cliente.dto.movie.manager.RequestMoviePaymentDTO;
import br.com.compass.filmes.cliente.exceptions.RentAndBuyMoviesEmptyException;
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
        return requestMoviePaymentDTO.getMovies().getRent() == null || requestMoviePaymentDTO.getMovies().getRent().isEmpty();
    }

    private boolean isBuyListEmptyOrNull(RequestMoviePaymentDTO requestMoviePaymentDTO) {
        return requestMoviePaymentDTO.getMovies().getBuy() == null || requestMoviePaymentDTO.getMovies().getBuy().isEmpty();
    }
}
