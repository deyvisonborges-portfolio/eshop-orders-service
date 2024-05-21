package com.deyvisonborges.service.orders.app.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.deyvisonborges.service.orders.core.domain.ValidationException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ErrorResponse> validationException(ValidationException d) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
        new ErrorResponse(
            d.getMessage(),
            HttpStatus.BAD_REQUEST,
            HttpStatus.BAD_REQUEST.value(),
            d.getErrors()));
  }
}
