package com.wg.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FeeNotAddedException extends RuntimeException {

	public FeeNotAddedException(String message) {
		super(message);
	}
}