package com.deyvisonborges.service.orders.app.exception.handler;

import java.util.ArrayList;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.deyvisonborges.service.orders.app.exception.NotFoundException;
import com.deyvisonborges.service.orders.core.domain.ValidationException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ErrorResponse> validationException(ValidationException d) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
      new ErrorResponse(d.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), d.getErrors())
    );
  }

  @ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> notFoundException(NotFoundException n) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
			new ErrorResponse(n.getMessage(), HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), new ArrayList<>())
		);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> badRequestException(BadRequestException n) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				new ErrorResponse(n.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), new ArrayList<>())
		);
	}
}
