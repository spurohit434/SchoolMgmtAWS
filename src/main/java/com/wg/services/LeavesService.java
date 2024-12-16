package com.wg.services;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wg.exceptions.LeaveCanNotApplyException;
import com.wg.helper.LoggingUtil;
import com.wg.model.Leaves;
import com.wg.repository.interfaces.InterfaceLeavesDAO;

@Service
public class LeavesService {
	private InterfaceLeavesDAO leavesDAO;
	Logger logger = LoggingUtil.getLogger(LeavesService.class);

	public LeavesService() {
	}

	@Autowired
	public LeavesService(InterfaceLeavesDAO leavesDAO) {
		this.leavesDAO = leavesDAO;
	}

	public boolean approveLeave(String userId) {
		boolean flag = false;
		try {
			flag = leavesDAO.approveLeave(userId);
			if (flag == true) {
				return flag;
			} else {
				return flag;
			}
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}

	public boolean rejectLeave(String userId) {
		boolean flag = false;
		try {
			flag = leavesDAO.rejectLeave(userId);
			if (flag == true) {
				System.out.println("Leave Rejected Successfully");
				return flag;
			} else {
				System.out.println("Leave Not rejected");
				return flag;
			}
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}

	public void applyLeave(Leaves leave) {
		try {
			String randomString = UUID.randomUUID().toString();
			int desiredLength = 7;
			if (desiredLength > randomString.length()) {
				desiredLength = randomString.length();
			}
			String leaveId = randomString.substring(0, desiredLength);
			leaveId = 'L' + leaveId;
			leave.setLeaveId(leaveId);
			boolean flag = leavesDAO.applyLeave(leave);
			if (flag == false) {
				throw new LeaveCanNotApplyException("Leave can not be applied " + leave.getUserId());
			}
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Leaves> viewAllLeave() {
		List<Leaves> leaves = null;
		try {
			leaves = leavesDAO.viewAllLeave();
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return leaves;
	}

	public List<Leaves> checkLeaveStatus(String userId) {
		List<Leaves> status = null;
		try {
			status = leavesDAO.checkLeaveStatus(userId);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

}
