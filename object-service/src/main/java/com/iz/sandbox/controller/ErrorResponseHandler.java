package com.iz.sandbox.controller;

import com.iz.sandbox.dto.Error;
import com.iz.sandbox.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ErrorResponseHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(createErrorResponse("Unable to parse request body", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(createErrorResponse("Wrong query parameter", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EntityNotFoundException.class, EmptyResultDataAccessException.class})
    protected ResponseEntity<Object> handleEntityNotFound(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(createErrorResponse("Entity Not found", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(createErrorResponse("Internal Server Error", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Response createErrorResponse(String decription, String details) {
        final Error error = new Error();
        error.setDescription(decription);
        error.setDetails(details);
        final Response response = new Response();
        response.setErrors(List.of(error));
        return response;
    }
}
