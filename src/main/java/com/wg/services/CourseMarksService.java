package com.wg.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wg.exceptions.GeneralException;
import com.wg.exceptions.NotFoundExceptions;
import com.wg.model.CourseMarks;
import com.wg.repository.interfaces.InterfaceCourseMarksDAO;

@Service
public class CourseMarksService {
	private InterfaceCourseMarksDAO courseMarksDAO;

	public CourseMarksService() {

	}

	@Autowired
	public CourseMarksService(InterfaceCourseMarksDAO courseMarksDAO) {
		this.courseMarksDAO = courseMarksDAO;
	}

	public boolean addMarks(CourseMarks courseMarks) {
		boolean flag = false;
		try {
			flag = courseMarksDAO.addMarks(courseMarks);
			return flag;
		} catch (ClassNotFoundException | SQLException e) {
			throw new GeneralException("Marks can not be added because " + e.getMessage());
		}
	}

	public List<CourseMarks> checkMarks(String userId) {
		List<CourseMarks> courseMarks = null;
		try {
			courseMarks = courseMarksDAO.checkMarks(userId);
			if (courseMarks == null) {
				throw new NotFoundExceptions("Marks not found for userID: " + userId);
			}
			return courseMarks;
		} catch (ClassNotFoundException | SQLException e) {
			throw new GeneralException("Marks can not be fetched because " + e.getMessage());
		}
	}
}