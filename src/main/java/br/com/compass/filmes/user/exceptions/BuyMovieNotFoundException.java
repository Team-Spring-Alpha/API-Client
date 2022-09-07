package br.com.compass.filmes.user.exceptions;

public class BuyMovieNotFoundException extends RuntimeException{

    public BuyMovieNotFoundException(String message) {
        super(message);
    }
}
