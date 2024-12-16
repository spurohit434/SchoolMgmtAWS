package com.wg.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wg.constants.AttendanceConstants;
import com.wg.model.Attendance;
import com.wg.model.Status;
import com.wg.repository.interfaces.InterfaceAttendanceDAO;

@Component
public class AttendanceDAO extends GenericDAO<Attendance> implements InterfaceAttendanceDAO {
	public AttendanceDAO() {
	}

	@Override
	protected Attendance mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		Attendance attendance = new Attendance();
		attendance.setStudentId(resultSet.getString(AttendanceConstants.STUDENT_ID_COLUMN));
		attendance.setStandard(resultSet.getInt(AttendanceConstants.STANDARD_COLUMN));
		attendance.setDate(resultSet.getDate(AttendanceConstants.DATE_COLUMN).toLocalDate());
		attendance.setStatus(Status.valueOf(resultSet.getString(AttendanceConstants.STATUS_COLUMN)));
		return attendance;
	}

	@Override
	public boolean addAttendance(Attendance attendance) throws SQLException, ClassNotFoundException {
		String query = "INSERT INTO Attendance (studentId, standard, date, status) VALUES ('"
				+ attendance.getStudentId() + "', " + attendance.getStandard() + ", '" + attendance.getDate() + "', '"
				+ attendance.getStatus() + "')";
		return executeQuery(query);
	}

	@Override
	public List<Attendance> viewAttendanceByStandard(int standard) throws SQLException, ClassNotFoundException {
		String query = "SELECT * FROM Attendance WHERE standard = " + standard;
		return executeGetAllQuery(query);
	}

	@Override
	public List<Attendance> viewAttendanceById(String studentId) throws SQLException, ClassNotFoundException {
		String query = "SELECT * FROM Attendance WHERE studentId = '" + studentId + "'";
		return executeGetAllQuery(query);
	}
}