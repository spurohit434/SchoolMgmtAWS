package com.wg.dto;

public class CourseMarksDTO {

	private String courseId;
	private String courseName;
	private double marks;

	public CourseMarksDTO(String courseId, double marks, String courseName) {
		this.courseId = courseId;
		this.marks = marks;
		this.courseName = courseName;
	}

	public CourseMarksDTO(String courseId, double marks) {
		this.courseId = courseId;
		this.marks = marks;
		// this.courseName = courseName;
	}

	public String getCourseId() {
		return courseId;
	}

	public double getMarks() {
		return marks;
	}

	public String getCourseName() {
		return courseName;
	}
}
