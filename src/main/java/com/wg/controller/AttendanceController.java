package com.wg.controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wg.dto.ApiResponseHandler;
import com.wg.model.Attendance;
import com.wg.model.StatusResponse;
import com.wg.services.AttendanceServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AttendanceController {
	private AttendanceServices attendanceService;
	Scanner scanner = new Scanner(System.in);

	@Autowired
	public AttendanceController(AttendanceServices attendanceService) {
		this.attendanceService = attendanceService;
	}

	public AttendanceController() {
	}

	@PostMapping("/user/{id}/attendance")
	public ResponseEntity<Object> addAttendance(@Valid @RequestBody Attendance attendance) {
		boolean flag = attendanceService.addAttendance(attendance);
		if (flag == true) {
			return ApiResponseHandler.apiResponseHandler("Attendance added Successfully", StatusResponse.Success,
					HttpStatus.CREATED, null);
		}

		return ApiResponseHandler.apiResponseHandler("Attendance can not be added", StatusResponse.Error,
				HttpStatus.BAD_REQUEST, null);
	}

	@GetMapping("/attendance/{standard}")
	public ResponseEntity<Object> viewAttendanceByStandard(@PathVariable int standard) {
		List<Attendance> list = null;
		list = attendanceService.viewAttendanceByStandard(standard);

		if (list == null) {
			return ApiResponseHandler.apiResponseHandler("Attendance can not be fetched", StatusResponse.Error,
					HttpStatus.BAD_REQUEST, list);
		}

		return ApiResponseHandler.apiResponseHandler("Attendance fetched Successfully", StatusResponse.Success,
				HttpStatus.OK, list);
	}

	@GetMapping("/user/{studentId}/attendance")
	public ResponseEntity<Object> viewAttendanceById(@PathVariable String studentId) {
		List<Attendance> list = null;
		list = attendanceService.viewAttendanceById(studentId);
		if (list == null) {
			return ApiResponseHandler.apiResponseHandler("Attendance can not be fetched", StatusResponse.Error,
					HttpStatus.BAD_REQUEST, list);
		}

		return ApiResponseHandler.apiResponseHandler("Attendance fetched Successfully", StatusResponse.Success,
				HttpStatus.OK, list);
	}
}
