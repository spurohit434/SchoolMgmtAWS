package com.wg.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wg.model.StatusResponse;

public class ApiResponseHandler {
	public static ResponseEntity<Object> apiResponseHandler(String message, StatusResponse status,
			HttpStatus statusCode, Object data) {
		ApiResponse response = new ApiResponse(message, status, data);
		return ResponseEntity.status(statusCode).body(response);
	}
}