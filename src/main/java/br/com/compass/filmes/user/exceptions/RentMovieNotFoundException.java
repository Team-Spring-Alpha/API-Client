package br.com.compass.filmes.user.exceptions;

public class RentMovieNotFoundException extends RuntimeException{
    public RentMovieNotFoundException(String message) {
        super(message);
    }
}
