package br.com.compass.filmes.user.exceptions;

public class CreditCardNotFoundException extends RuntimeException{

    public CreditCardNotFoundException(String message) {
        super(message);
    }
}
