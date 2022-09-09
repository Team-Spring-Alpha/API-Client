package br.com.compass.filmes.user.exceptions;

public class UserAuthInvalidException extends RuntimeException{
    public UserAuthInvalidException(String message) {
        super(message);
    }
}
