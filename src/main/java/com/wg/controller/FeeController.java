package com.wg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wg.dto.ApiResponseHandler;
import com.wg.dto.FeeResponse;
import com.wg.model.Fee;
import com.wg.model.StatusResponse;
import com.wg.services.FeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class FeeController {
	private FeeService feeService;

	public FeeController() {
	}

	@Autowired
	public FeeController(FeeService feeService) {
		this.feeService = feeService;
	}

	@PatchMapping("/user/{id}/fee")
	public ResponseEntity<Object> payFees(@PathVariable String id) {
		double amount = feeService.payFees(id);
		return ApiResponseHandler.apiResponseHandler("Fee paid Successfully", StatusResponse.Success, HttpStatus.OK,
				amount);
	}

	@GetMapping("/user/{id}/fee")
	public ResponseEntity<Object> checkFees(@PathVariable String id) {
		double fees = feeService.checkFees(id);
		return ApiResponseHandler.apiResponseHandler("Fee fetched Successfully", StatusResponse.Success, HttpStatus.OK,
				fees);
	}

	@PutMapping("/user/{id}/fee")
	public ResponseEntity<FeeResponse> updateFees(@RequestBody Fee fee, @PathVariable String id) {
		return ResponseEntity.ok(feeService.updateFees(fee, id));
	}

	@PostMapping("/user/{id}/fee")
	public ResponseEntity<FeeResponse> addFees(@Valid @RequestBody Fee fee, @PathVariable String id) {
		return ResponseEntity.created(null).body(feeService.addFees(fee, id));
	}

	@GetMapping("user/{id}/fine")
	public ResponseEntity<Object> calculateFine(String id) {
		double fees = feeService.calculateFine(id);
		return ApiResponseHandler.apiResponseHandler("Fine fetched Successfully", StatusResponse.Success, HttpStatus.OK,
				fees);
	}
}