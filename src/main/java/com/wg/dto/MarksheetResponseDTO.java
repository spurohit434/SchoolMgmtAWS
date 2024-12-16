package com.wg.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarksheetResponseDTO {

	private String userId;
	private List<CourseMarksDTO> courseMarks;
	private double totalMarks;
	private double percentage;
	private String result; // "Pass" or "Fail"

	public MarksheetResponseDTO(String userId, List<CourseMarksDTO> courseMarks, double totalMarks, double percentage,
			String result) {
		this.userId = userId;
		this.courseMarks = courseMarks;
		this.totalMarks = totalMarks;
		this.percentage = percentage;
		this.result = result;
	}
}
