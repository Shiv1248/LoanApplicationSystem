package com.loan.application.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MaximumRequestLimitReachedException.class)
    public ResponseEntity<ErrorResponse> handleMaximumRequestLimitReachedException(MaximumRequestLimitReachedException ex) {
        String errorMessage = ex.getMessage();
        int maxRequestLimit = ex.getMaxRequestLimit();

        ErrorResponse errorResponse = new ErrorResponse(errorMessage, maxRequestLimit);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ApplicationNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleApplicationNotFoundException(ApplicationNotFoundException ex) {
        String errorMessage = ex.getMessage();

        ErrorResponse errorResponse = new ErrorResponse(errorMessage, 404);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,Object> errorMap=new LinkedHashMap<>();

        errorMap.put("Message:", "You are not using the correct Request Method.");

        return new ResponseEntity<Object>(errorMap, HttpStatus.BAD_REQUEST);
    }
}