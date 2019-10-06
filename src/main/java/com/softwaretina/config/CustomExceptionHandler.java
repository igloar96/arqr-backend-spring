package com.softwaretina.config;

import com.softwaretina.models.exception.NoAutorizadoException;
import com.softwaretina.models.exception.NoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NoAutorizadoException.class} )
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void manageNotAuthorization() {

    }

    @ExceptionHandler({NoEncontradoException.class} )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void manageNotFound() {

    }

}
