package com.wg.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wg.dto.MarksheetResponseDTO;
import com.wg.exceptions.NotFoundExceptions;
import com.wg.mapper.MarksheetMapper;
import com.wg.model.CourseMarks;
import com.wg.model.User;
import com.wg.services.CourseMarksService;
import com.wg.services.UserService;

@RestController
@RequestMapping("/api")
public class MarksheetController {

	public CourseMarksService courseMarksService;
	public UserService userService;

	public MarksheetController(CourseMarksService courseMarksService, UserService userService) {
		this.courseMarksService = courseMarksService;
		this.userService = userService;
	}

	@GetMapping("/user/{id}/marksheet")
	public MarksheetResponseDTO generateMarksheet(@PathVariable String id) {
		User user = userService.getUserById(id);
		if (user == null) {
			throw new NotFoundExceptions("User not found for id: " + id);
		}
		List<CourseMarks> courseMarks = courseMarksService.checkMarks(id);
		if (courseMarks.isEmpty()) {
			throw new NotFoundExceptions("No course marks found for user id: " + id);
		}
		// Generate marksheet
		return MarksheetMapper.toMarksheetResponse(id, courseMarks);
	}
}
