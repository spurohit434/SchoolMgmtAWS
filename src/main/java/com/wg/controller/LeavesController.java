package com.wg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wg.dto.ApiResponseHandler;
import com.wg.model.Leaves;
import com.wg.model.StatusResponse;
import com.wg.services.LeavesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class LeavesController {
	private LeavesService leavesService;

	@Autowired
	public LeavesController(LeavesService leavesService) {
		this.leavesService = leavesService;
	}

	public LeavesController() {
	}

	@PatchMapping("/user/{id}/leave/approve")
	public ResponseEntity<Object> approveLeave(@PathVariable String id) {
		boolean flag = leavesService.approveLeave(id);
		if (flag == true) {
			return ApiResponseHandler.apiResponseHandler("Leaves approved Successfully", StatusResponse.Success,
					HttpStatus.OK, null);
		}

		return ApiResponseHandler.apiResponseHandler("Leaves can not be approved", StatusResponse.Error,
				HttpStatus.BAD_REQUEST, null);
	}

	@PatchMapping("/user/{id}/leave/reject")
	public ResponseEntity<Object> rejectLeave(@PathVariable String id) {
		boolean flag = leavesService.rejectLeave(id);
		if (flag == true) {
			return ApiResponseHandler.apiResponseHandler("Leaves rejected Successfully", StatusResponse.Success,
					HttpStatus.OK, null);
		}

		return ApiResponseHandler.apiResponseHandler("Leaves can not be rejected", StatusResponse.Error,
				HttpStatus.BAD_REQUEST, null);
	}

	@PatchMapping("/user/{id}/leave")
	public ResponseEntity<Object> updateLeaveStatus(@PathVariable String id,
			@RequestParam(required = true) String action) {
		if ("approve".equals(action)) {
			boolean isApproved = leavesService.approveLeave(id);
			if (isApproved == true) {
				return ApiResponseHandler.apiResponseHandler("Leaves approved Successfully", StatusResponse.Success,
						HttpStatus.OK, null);
			}
		} else if ("reject".equals(action)) {
			boolean isRejected = leavesService.rejectLeave(id);
			if (isRejected == true) {
				return ApiResponseHandler.apiResponseHandler("Leaves rejected Successfully", StatusResponse.Success,
						HttpStatus.OK, null);
			}
		}
		return ApiResponseHandler.apiResponseHandler("Invalid action", StatusResponse.Error, HttpStatus.BAD_REQUEST,
				null);
	}

	@PostMapping("/user/{id}/leave")
	public ResponseEntity<Object> applyLeave(@Valid @RequestBody Leaves leave, @PathVariable String id) {
		leave.setUserId(id);
		leavesService.applyLeave(leave);
		return ApiResponseHandler.apiResponseHandler("Leaves applied Successfully", StatusResponse.Success,
				HttpStatus.OK, leave);
	}

	@GetMapping("/leave")
	public ResponseEntity<Object> viewAllLeave() {
		List<Leaves> leaves = leavesService.viewAllLeave();
		return ApiResponseHandler.apiResponseHandler("Leaves Fetched Successfully", StatusResponse.Success,
				HttpStatus.OK, leaves);
	}

	@GetMapping("/user/{id}/leave")
	public ResponseEntity<Object> checkLeaveStatus(@PathVariable String id) {
		List<Leaves> leaves = leavesService.checkLeaveStatus(id);
		return ApiResponseHandler.apiResponseHandler("Leaves Fetched Successfully", StatusResponse.Success,
				HttpStatus.OK, leaves);
	}
}