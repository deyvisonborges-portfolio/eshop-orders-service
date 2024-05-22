package com.deyvisonborges.service.orders.app.exception;

public class BadRequestException extends NoStackTracingException{
	public BadRequestException(String message) {
		super(message);
	}
}
