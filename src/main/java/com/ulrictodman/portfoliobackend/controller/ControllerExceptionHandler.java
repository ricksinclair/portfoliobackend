package com.ulrictodman.portfoliobackend.controller;

import com.ulrictodman.portfoliobackend.exception.ApiExceptionResponse;
import com.ulrictodman.portfoliobackend.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiExceptionResponse response = new ApiExceptionResponse(e.getMessage(),
                status,
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(value = { IllegalStateException.class}  )
    public ResponseEntity<Object> handleOtherExceptions(RuntimeException e){

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ApiExceptionResponse response = new ApiExceptionResponse(e.getMessage(),  status, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String name = ex.getName();
        String type = Objects.requireNonNull(ex.getRequiredType()).getSimpleName();
        Object value = ex.getValue();
        String message = String.format("'%s' should be a valid '%s' and '%s' isn't",
                name, type, value);

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ApiExceptionResponse response = new ApiExceptionResponse(message,  status, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(response, status);
    }
}
