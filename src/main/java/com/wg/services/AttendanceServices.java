package com.wg.services;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wg.exceptions.GeneralException;
import com.wg.exceptions.StandardNotFoundException;
import com.wg.helper.LoggingUtil;
import com.wg.model.Attendance;
import com.wg.repository.interfaces.InterfaceAttendanceDAO;

@Service
public class AttendanceServices {
	private InterfaceAttendanceDAO attendanceDAO;
	Logger logger = LoggingUtil.getLogger(AttendanceServices.class);

	public AttendanceServices() {
	}

	@Autowired
	public AttendanceServices(InterfaceAttendanceDAO attendanceDAO) {
		this.attendanceDAO = attendanceDAO;
	}

	public List<Attendance> viewAttendanceByStandard(int standard) {
		if (standard < 1 || standard > 12) {
			throw new StandardNotFoundException("This standard is invalid");
		}
		List<Attendance> list = null;
		try {
			list = attendanceDAO.viewAttendanceByStandard(standard);
			if (list.isEmpty()) {
				throw new StandardNotFoundException("Attendance not found for standard: " + standard);
			}
		} catch (SQLException | ClassNotFoundException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	public List<Attendance> viewAttendanceById(String studentId) {
		List<Attendance> list = null;
		try {
			list = attendanceDAO.viewAttendanceById(studentId);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	public boolean addAttendance(Attendance attendance) {
		boolean flag = false;
		try {
			flag = attendanceDAO.addAttendance(attendance);
			return flag;
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
			throw new GeneralException(e.getMessage());
		}
	}
}
