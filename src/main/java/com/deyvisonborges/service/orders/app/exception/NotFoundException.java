package com.deyvisonborges.service.orders.app.exception;

public class NotFoundException extends NoStackTracingException{
	public NotFoundException(String message) {
		super(message);
	}
}