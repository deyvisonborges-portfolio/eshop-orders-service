package com.deyvisonborges.service.orders.app.exception.handler;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
  String message,
  HttpStatus httpStatus,
  Integer statusCode,
  Object errors
) {}
