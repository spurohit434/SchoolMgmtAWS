package com.wg.repository.interfaces;

import java.sql.SQLException;

import com.wg.model.Fee;
import com.wg.model.Transactions;

public interface InterfaceFeeDAO {

	public Fee checkFees(String studentId) throws SQLException, ClassNotFoundException;

	public boolean payFees(String studentId) throws SQLException, ClassNotFoundException;

	public boolean insertFees(Fee fee) throws ClassNotFoundException, SQLException;

	public boolean updateFees(Fee fee) throws ClassNotFoundException, SQLException;

	public void addFees(Fee fee) throws SQLException, ClassNotFoundException;

	public Fee calculateFine(String studentId) throws SQLException, ClassNotFoundException;

	public boolean addTransaction(Transactions transaction) throws ClassNotFoundException, SQLException;
}