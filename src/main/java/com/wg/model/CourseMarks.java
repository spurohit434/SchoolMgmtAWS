package com.wg.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseMarks {

	private String userId;
	@NotNull(message = "course id can not be null")
	private String courseId;
	@NotNull
	@Max(value = 100, message = "maximum marks can be 100")
	@Min(value = 0, message = "minimum marks can be 0")
	private double marks;
	private int standard;

	public CourseMarks(String userId, String courseId, double marks, int standard) {
		this.userId = userId;
		this.courseId = courseId;
		this.marks = marks;
		this.standard = standard;
	}

	public CourseMarks() {

	}
}