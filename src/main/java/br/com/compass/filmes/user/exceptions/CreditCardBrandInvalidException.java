package br.com.compass.filmes.user.exceptions;

public class CreditCardBrandInvalidException extends RuntimeException {
    public CreditCardBrandInvalidException(String message) {
        super(message);
    }
}
