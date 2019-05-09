package com.tv.manager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE_WITH_COMPLEMENT = "User does not exist: ";
	private static final String MESSAGE = "User does not exist.";

	public UserNotFoundException() {
		super(MESSAGE);
	}

	public UserNotFoundException(String message) {
		super(MESSAGE_WITH_COMPLEMENT.concat(message));
	}
}
