package com.wg.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.wg.dto.ApiResponseHandler;
import com.wg.model.StatusResponse;

@RestController
public class PathNotFoundErrorController {
	// @RequestMapping("/**")
	public ResponseEntity<Object> handleUndefinedPaths() {
		return ApiResponseHandler.apiResponseHandler("Page not found", StatusResponse.Error, HttpStatus.NOT_FOUND,
				null);
	}
}