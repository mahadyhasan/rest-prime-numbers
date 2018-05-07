package com.example.primes.controller;

import com.example.primes.domain.ApiErrorResponse;
import com.example.primes.exception.InvalidParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(InvalidParameterException.class)
    public ApiErrorResponse handleInvalidParmeters(InvalidParameterException ex) {

        ApiErrorResponse response = new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(HttpStatus.BAD_REQUEST)
                .withError_code("BAD_REQUEST")
                .withMessage(ex.getLocalizedMessage()).build();

        return response;

    }

}
