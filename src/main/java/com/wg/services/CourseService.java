package com.wg.services;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wg.exceptions.NotFoundExceptions;
import com.wg.helper.LoggingUtil;
import com.wg.model.Course;
import com.wg.repository.interfaces.InterfaceCourseDAO;

@Service
public class CourseService {
	private InterfaceCourseDAO courseDAO;

	Logger logger = LoggingUtil.getLogger(CourseService.class);

	public CourseService() {

	}

	@Autowired
	public CourseService(InterfaceCourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public Course getCourse(String courseId) {
		Course course = null;
		try {
			course = courseDAO.getCourse(courseId);
			if (course == null) {
				System.out.println("No course found");
			}
			return course;
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return course;
	}

	public List<Course> getAllCourses() {
		List<Course> list = null;
		try {
			list = courseDAO.getAllCourses();
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	public boolean addCourse(Course course) {
		boolean flag = false;
		try {
			flag = courseDAO.addCourse(course);
			if (flag == true) {
				System.out.println("Course Added successfully");
				return flag;
			} else {
				System.out.println("Course not added");
				return flag;
			}
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}

	public boolean deleteCourse(String id) {
		Course course = null;
		try {
			course = courseDAO.getCourse(id);
			if (course != null) {
				return courseDAO.deleteCourse(id);
			} else {
				throw new NotFoundExceptions("Course not found for id: " + id);
			}
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateCourse(String courseId, Course course) {
		Course course1 = null;
		boolean flag = false;
		try {
			course1 = courseDAO.getCourse(courseId);
			if (course1 == null) {
				throw new NotFoundExceptions("Course not found for id: " + courseId);
			}
			flag = courseDAO.updateCourse(courseId, course);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}

}
