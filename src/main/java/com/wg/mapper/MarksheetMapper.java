package com.wg.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.wg.dto.CourseMarksDTO;
import com.wg.dto.MarksheetResponseDTO;
import com.wg.model.CourseMarks;

public class MarksheetMapper {

	public static MarksheetResponseDTO toMarksheetResponse(String userId, List<CourseMarks> courseMarksList) {
		List<CourseMarksDTO> courseMarksDTOs = courseMarksList.stream()
				.map(marks -> new CourseMarksDTO(marks.getCourseId(), marks.getMarks())).collect(Collectors.toList());

		double totalMarks = courseMarksList.stream().mapToDouble(CourseMarks::getMarks).sum();

		double percentage = totalMarks / courseMarksList.size();

		// Determine pass/fail (assumes passing marks >= 40 for each course)
		boolean isPass = courseMarksList.stream().allMatch(marks -> marks.getMarks() >= 40);
		String result = isPass ? "Pass" : "Fail";

		return new MarksheetResponseDTO(userId, courseMarksDTOs, totalMarks, percentage, result);
	}
}
