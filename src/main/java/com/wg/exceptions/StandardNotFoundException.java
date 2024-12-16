package com.wg.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class StandardNotFoundException extends RuntimeException {

	public StandardNotFoundException(String message) {
		super(message);
	}

}