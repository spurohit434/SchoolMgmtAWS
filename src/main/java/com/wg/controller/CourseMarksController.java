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
import com.wg.exceptions.InvalidInputException;
import com.wg.model.CourseMarks;
import com.wg.model.StatusResponse;
import com.wg.services.CourseMarksService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CourseMarksController {
	private CourseMarksService courseMarksService;
	Scanner scanner = new Scanner(System.in);

	public CourseMarksController() {
	}

	@Autowired
	public CourseMarksController(CourseMarksService courseMarksService) {
		this.courseMarksService = courseMarksService;
	}

	@PostMapping("user/{userId}/marks")
	public ResponseEntity<Object> addMarks(@Valid @RequestBody CourseMarks courseMarks) {
		double marks = courseMarks.getMarks();
		if (marks > 100 || marks < 0) {
			throw new InvalidInputException("Enter valid Marks");
		}
		boolean flag = courseMarksService.addMarks(courseMarks);
		if (flag == false) {
			return ApiResponseHandler.apiResponseHandler("Marks can not be added", StatusResponse.Error,
					HttpStatus.BAD_REQUEST, marks);
		}
		return ApiResponseHandler.apiResponseHandler("Marks added Successfully", StatusResponse.Success,
				HttpStatus.CREATED, marks);
	}

	@GetMapping("/user/{userId}/marks")
	public ResponseEntity<Object> checkMarks(@PathVariable String userId) {
		List<CourseMarks> marks = null;
		marks = courseMarksService.checkMarks(userId);

		if (marks == null) {
			return ApiResponseHandler.apiResponseHandler("Marks can not be fetched", StatusResponse.Error,
					HttpStatus.BAD_REQUEST, marks);
		}
		return ApiResponseHandler.apiResponseHandler("Marks fetched Successfully", StatusResponse.Success,
				HttpStatus.OK, marks);
	}
}