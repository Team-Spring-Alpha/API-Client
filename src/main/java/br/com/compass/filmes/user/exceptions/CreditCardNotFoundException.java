package br.com.compass.filmes.cliente.exceptions;

public class CreditCardNotFoundException extends RuntimeException{

    public CreditCardNotFoundException(String message) {
        super(message);
    }
}
