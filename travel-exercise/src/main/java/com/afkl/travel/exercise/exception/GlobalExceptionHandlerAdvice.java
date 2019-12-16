package com.afkl.travel.exercise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<LocationErrorResponse> handleLocationNotFoundException(
            LocationNotFoundException ex) {
        final LocationErrorResponse error = new LocationErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<LocationErrorResponse>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<LocationErrorResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {
        final LocationErrorResponse error = new LocationErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("The type for the argument is invalid. Please insert the correct type.");
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<LocationErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<LocationErrorResponse> handleException(Exception ex) {
        final LocationErrorResponse error = new LocationErrorResponse();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<LocationErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
