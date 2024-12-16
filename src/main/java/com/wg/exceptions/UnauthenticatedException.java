package com.wg.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UnauthenticatedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnauthenticatedException(String message) {
		super(message);
	}
}