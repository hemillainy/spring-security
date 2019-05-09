package com.tv.manager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FailedAuthenticationException extends AuthenticationException {
	private static final String MESSAGE_WIRH_COMPLEMENT = "Bad credentials: ";
	private static final String MESSAGE = "Bad credentials.";
	private static final long serialVersionUID = 1L;

	public FailedAuthenticationException() {
		super(MESSAGE);
	}
	
	public FailedAuthenticationException(String message) {
		super(MESSAGE_WIRH_COMPLEMENT.concat(message));
	}
}