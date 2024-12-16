package com.wg.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LeaveCanNotApplyException extends RuntimeException {

	public LeaveCanNotApplyException(String message) {
		super(message);
	}
}