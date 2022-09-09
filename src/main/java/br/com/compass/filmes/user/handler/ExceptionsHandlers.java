package br.com.compass.filmes.user.handler;

import br.com.compass.filmes.user.exceptions.*;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandlers {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionResponseDto>> handleInvalidArgument(MethodArgumentNotValidException exception) {
        List<ExceptionResponseDto> responseDTOList = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ExceptionResponseDto error = new ExceptionResponseDto(e.getField(), message);
            responseDTOList.add(error);
        });
        return ResponseEntity.badRequest().body(responseDTOList);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponseDto> handleHttpValidationExceptions(HttpMessageNotReadableException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorMessage = "Invalid information";

        if (e.getCause() instanceof InvalidFormatException) {
            InvalidFormatException cause = (InvalidFormatException) e.getCause();
            errorMessage = cause.getCause().getMessage();
        }
        return ResponseEntity.status(status).body(new ExceptionResponseDto(String.valueOf(status.value()), errorMessage));

    }

    @ExceptionHandler(CreditCardNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handlerCreditCardNotFoundException(CreditCardNotFoundException creditCardNotFoundException) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(creditCardNotFoundException.getMessage(), "credit_card_number");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }
    @ExceptionHandler(RentAndBuyMoviesEmptyException.class)
    public ResponseEntity<ExceptionResponseDto> handlerRentAndBuyMoviesEmptyException(RentAndBuyMoviesEmptyException rentAndBuyMoviesEmptyException) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto( "movies buy list and rent list is empty. Either list must be filled", "movies");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handlerClientNotFoundException(UserNotFoundException userNotFoundException) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(userNotFoundException.getMessage(), "user_id");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }
    @ExceptionHandler(BuyMovieNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handlerBuyMovieNotFoundException(BuyMovieNotFoundException buyMovieNotFoundException) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(buyMovieNotFoundException.getMessage(), "movie.buy");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }
    @ExceptionHandler(RentMovieNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handlerRentMovieNotFoundException(RentMovieNotFoundException rentMovieNotFoundException) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(rentMovieNotFoundException.getMessage(), "movie.rent");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }
    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handlerMovieNotFoundException(MovieNotFoundException movieNotFoundException) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(movieNotFoundException.getMessage(), "movie");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    @ExceptionHandler(CreditCardBrandInvalidException.class)
    public ResponseEntity<ExceptionResponseDto> handlerCreditCardBrandInvalidException(CreditCardBrandInvalidException creditCardBrandInvalidException) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(creditCardBrandInvalidException.getMessage(), "cards.brand");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @ExceptionHandler(CreditCardMonthExpirationInvalidException.class)
    public ResponseEntity<ExceptionResponseDto> handlerCreditCardMonthExpirationInvalidException(CreditCardMonthExpirationInvalidException creditCardMonthExpirationInvalidException) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto("must be between 1-12", "cards.month_expiration");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @ExceptionHandler(CreditCardSecurityCodeInvalidException.class)
    public ResponseEntity<ExceptionResponseDto> handlerCreditCardSecurityCodeInvalidException(CreditCardSecurityCodeInvalidException cardSecurityCodeInvalidException) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto("must be 3 digits e.g (010)", "cards.security_code");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @ExceptionHandler(CreditCardYearExpirationInvalidException.class)
    public ResponseEntity<ExceptionResponseDto> handlerCreditCardYearExpirationInvalidException(CreditCardYearExpirationInvalidException creditCardYearExpirationInvalidException) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto("must be a valid year, and between year now and year now + 5", "cards.security_code");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public final ResponseEntity<ExceptionResponse> handleInvalidJwtAuthenticationExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(RequiredObjectisNullException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(UserAuthInvalidException.class)
    public ResponseEntity<ExceptionResponseDto> handlerUserAuthInvalidException(UserAuthInvalidException userAuthInvalidException) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(userAuthInvalidException.getMessage(), "email or password incorrect");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @ExceptionHandler(StoreNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handlerStoreNotFoundException(StoreNotFoundException storeNotFoundException) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(storeNotFoundException.getMessage(), "movie.store");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handlerUsernameNotFoundException(UsernameNotFoundException usernameNotFoundException) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(usernameNotFoundException.getMessage(),  "username.param");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }
}
