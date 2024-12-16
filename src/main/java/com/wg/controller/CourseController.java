package com.wg.controller;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wg.dto.ApiResponseHandler;
import com.wg.model.Course;
import com.wg.model.StatusResponse;
import com.wg.services.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CourseController {

	public static final String STANDARD = "Standard";
	public static final String COURSE_NAME = "CourseName";
	private CourseService courseService;
	Scanner scanner = new Scanner(System.in);

	@Autowired
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	public CourseController() {
	}

	@GetMapping("/course")
	public ResponseEntity<Object> getAllCourses() {
		List<Course> course = null;
		course = courseService.getAllCourses();
		return ApiResponseHandler.apiResponseHandler("Courses fetched successfully", StatusResponse.Success,
				HttpStatus.OK, course);
	}

	@PutMapping("/course/{id}")
	public ResponseEntity<Object> updateCourse(@PathVariable String id, @RequestBody Course updatedCourse) {
		boolean flag = courseService.updateCourse(id, updatedCourse);
		if (flag == true) {
			return ApiResponseHandler.apiResponseHandler("Course Updated Successfully", StatusResponse.Success,
					HttpStatus.OK, null);
		}
		return ApiResponseHandler.apiResponseHandler("Course can not be updated", StatusResponse.Error,
				HttpStatus.BAD_REQUEST, null);
	}

	@DeleteMapping("/course/{courseId}")
	public ResponseEntity<Object> deleteCourse(@PathVariable String courseId) {
		boolean flag = false;
		flag = courseService.deleteCourse(courseId);
		if (flag == false) {
			return ApiResponseHandler.apiResponseHandler("Course can not be deleted", StatusResponse.Error,
					HttpStatus.BAD_REQUEST, null);
		}
		return ApiResponseHandler.apiResponseHandler("Course deleted successfully", StatusResponse.Success,
				HttpStatus.OK, null);
	}

	@PostMapping("/course")
	public ResponseEntity<Object> addCourse(@Valid @RequestBody Course course) {
		String courseName = course.getCourseName();
		int standard = course.getStandard();
		String randomString = UUID.randomUUID().toString();
		int desiredLength = 8;
		if (desiredLength > randomString.length()) {
			desiredLength = randomString.length();
		}
		String courseId = randomString.substring(0, desiredLength);
		course.setCourseId(courseId);
		if (courseName == null || standard > 12) {
			return ApiResponseHandler.apiResponseHandler("Enter valid Credentials", StatusResponse.Error,
					HttpStatus.BAD_REQUEST, course);
		}
		boolean flag = courseService.addCourse(course);
		if (flag == true) {
			return ApiResponseHandler.apiResponseHandler("Course added Successfully", StatusResponse.Success,
					HttpStatus.OK, course);
		}
		return ApiResponseHandler.apiResponseHandler("Course can not be added successfully", StatusResponse.Error,
				HttpStatus.BAD_REQUEST, course);
	}

	@GetMapping("/course/{courseId}")
	public ResponseEntity<Object> getCourse(@PathVariable String courseId) {
		Course course = null;
		course = courseService.getCourse(courseId);
		if (course == null) {
			return ApiResponseHandler.apiResponseHandler("Course not found", StatusResponse.Error, HttpStatus.NOT_FOUND,
					null);
		}
		return ApiResponseHandler.apiResponseHandler("Course Fetched Successfully", StatusResponse.Success,
				HttpStatus.OK, course);
	}
}