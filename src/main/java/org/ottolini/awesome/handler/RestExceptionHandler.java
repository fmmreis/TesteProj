package org.ottolini.awesome.handler;


import org.ottolini.awesome.error.ResourceNotFoundDetails;
import org.ottolini.awesome.error.ResourceNotFoundException;
import org.ottolini.awesome.error.ValidationErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rfnException){

        ResourceNotFoundDetails rnfDetails = ResourceNotFoundDetails.Builder.newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not found")
                .detail(rfnException.getMessage())
                .developerMessage(rfnException.getClass().getName())
                .build();

        return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException manvException){
        List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();

        ValidationErrorDetails rnfDetails = ValidationErrorDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .title("Field Validation Error")
                .detail("Field Validation Error")
                .developerMessage(manvException.getClass().getName())
                .build();

        return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
    }
}

