package br.com.compass.filmes.cliente.exceptions;

public class RentMovieNotFoundException extends RuntimeException{
    public RentMovieNotFoundException(String message) {
        super(message);
    }
}
