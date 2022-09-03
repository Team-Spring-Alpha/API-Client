package br.com.compass.filmes.cliente.exceptions;

public class BuyMovieNotFoundException extends RuntimeException{

    public BuyMovieNotFoundException(String message) {
        super(message);
    }
}
