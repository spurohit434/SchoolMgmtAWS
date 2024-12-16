package com.wg.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wg.constants.CourseConstants;
import com.wg.model.Course;
import com.wg.repository.interfaces.InterfaceCourseDAO;

@Component
public class CourseDAO extends GenericDAO<Course> implements InterfaceCourseDAO {

	public CourseDAO() {
	}

	@Override
	protected Course mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		Course course = new Course();
		course.setCourseId(resultSet.getString(CourseConstants.COURSE_ID));
		course.setCourseName(resultSet.getString(CourseConstants.COURSE_NAME));
		course.setStandard(resultSet.getInt(CourseConstants.STANDARD));
		return course;
	}

	@Override
	public Course getCourse(String courseId) throws SQLException, ClassNotFoundException {
		String selectSQL = "SELECT * FROM Course WHERE courseId = \"" + courseId + "\"";
		return executeGetQuery(selectSQL);
	}

	@Override
	public List<Course> getAllCourses() throws SQLException, ClassNotFoundException {
		String selectSQL = "SELECT * FROM Course";
		return executeGetAllQuery(selectSQL);
	}

	@Override
	public boolean deleteCourse(String id) throws SQLException, ClassNotFoundException {
		String selectSQL = "DELETE FROM Course WHERE courseId = \"" + id + "\"";
		return executeQuery(selectSQL);
	}

	@Override
	public boolean addCourse(Course course) throws SQLException, ClassNotFoundException {
		String sqlQuery = String.format("INSERT INTO Course (courseId, courseName, standard) VALUES ('%s','%s','%s')",
				course.getCourseId(), course.getCourseName(), course.getStandard());
		return executeQuery(sqlQuery);
	}

	@Override
	public boolean updateCourse(String courseId, Course course) throws SQLException, ClassNotFoundException {
		String sqlQuery = String.format(
				"UPDATE Course SET coursename = '%s', " + "standard = '%s' " + "WHERE 	courseId = '%s';",
				course.getCourseName(), course.getStandard(), courseId);
		Boolean flag = null;
		System.out.println(sqlQuery);
		try {
			flag = executeQuery(sqlQuery);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
}