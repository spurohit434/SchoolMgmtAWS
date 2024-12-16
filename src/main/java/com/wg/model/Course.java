package com.wg.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Course {
	String courseId;
	@NotNull(message = "Course name can not be null")
	String CourseName;
	@NotNull(message = "Standard can not be null")
	@Max(value = 12, message = "Course standard should be less than 12")
	@Min(value = 1, message = "Course standard should be greater than 1")
	int standard;

	public Course(String courseId, String courseName, int standard) {
		super();
		this.courseId = courseId;
		CourseName = courseName;
		this.standard = standard;
	}

	public Course() {
	}
}