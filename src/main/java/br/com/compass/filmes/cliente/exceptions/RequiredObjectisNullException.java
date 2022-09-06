package br.com.compass.filmes.cliente.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class RequiredObjectisNullException extends AuthenticationException{

    public RequiredObjectisNullException(String ex) {
        super(ex);
    }
}
