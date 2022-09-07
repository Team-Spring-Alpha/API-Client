package br.com.compass.filmes.user.handler;

import br.com.compass.filmes.user.exceptions.*;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandlers {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionResponse>> handleInvalidArgument(MethodArgumentNotValidException exception) {
        List<ExceptionResponse> responseDTOList = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ExceptionResponse error = new ExceptionResponse(e.getField(), message);
            responseDTOList.add(error);
        });
        return ResponseEntity.badRequest().body(responseDTOList);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpValidationExceptions(HttpMessageNotReadableException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorMessage = "Invalid information";

        if (e.getCause() instanceof InvalidFormatException) {
            InvalidFormatException cause = (InvalidFormatException) e.getCause();
            errorMessage = cause.getCause().getMessage();
        }
        return ResponseEntity.status(status).body(new ExceptionResponse(String.valueOf(status.value()), errorMessage));

    }

    @ExceptionHandler(CreditCardNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlerCreditCardNotFoundException(CreditCardNotFoundException creditCardNotFoundException) {
        ExceptionResponse responseDto = new ExceptionResponse(creditCardNotFoundException.getMessage(), "credit_card_number");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    @ExceptionHandler(RentAndBuyMoviesEmptyException.class)
    public ResponseEntity<ExceptionResponse> handlerRentAndBuyMoviesEmptyException(RentAndBuyMoviesEmptyException rentAndBuyMoviesEmptyException) {
        ExceptionResponse responseDto = new ExceptionResponse( "movies buy list and rent list is empty. Either list must be filled", "movies");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlerClientNotFoundException(UserNotFoundException userNotFoundException) {
        ExceptionResponse responseDto = new ExceptionResponse(userNotFoundException.getMessage(), "user_id");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    @ExceptionHandler(BuyMovieNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlerBuyMovieNotFoundException(BuyMovieNotFoundException buyMovieNotFoundException) {
        ExceptionResponse responseDto = new ExceptionResponse(buyMovieNotFoundException.getMessage(), "movie.buy");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    @ExceptionHandler(RentMovieNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlerRentMovieNotFoundException(RentMovieNotFoundException rentMovieNotFoundException) {
        ExceptionResponse responseDto = new ExceptionResponse(rentMovieNotFoundException.getMessage(), "movie.rent");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlerMovieNotFoundException(MovieNotFoundException movieNotFoundException) {
        ExceptionResponse responseDto = new ExceptionResponse(movieNotFoundException.getMessage(), "movie");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }


}
