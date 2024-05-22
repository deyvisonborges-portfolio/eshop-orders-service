package com.deyvisonborges.service.orders.app.exception;

public class NoStackTracingException extends RuntimeException {
	public NoStackTracingException(final String message) {
		super(message, null);
	}

	public NoStackTracingException(final String message, final Throwable cause) {
		super(message, cause, true, false);
	}
}
