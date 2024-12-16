package com.wg.dto;

import com.wg.model.StatusResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
	private String message;
	private StatusResponse status;
	private Object data;

	public ApiResponse(String message, StatusResponse status, Object data) {
		this.message = message;
		this.status = status;
		this.data = data;
	}
}