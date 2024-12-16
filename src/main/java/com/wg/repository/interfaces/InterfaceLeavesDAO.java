package com.wg.repository.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.wg.model.Leaves;

public interface InterfaceLeavesDAO {

	public boolean approveLeave(String userId) throws SQLException, ClassNotFoundException;

	public boolean rejectLeave(String userId) throws SQLException, ClassNotFoundException;

	public boolean applyLeave(Leaves leave) throws SQLException, ClassNotFoundException;

	public List<Leaves> viewAllLeave() throws ClassNotFoundException, SQLException;

	public List<Leaves> checkLeaveStatus(String userId) throws ClassNotFoundException, SQLException;
}