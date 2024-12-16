package com.wg.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.wg.model.Fee;
import com.wg.model.Transactions;
import com.wg.repository.interfaces.InterfaceFeeDAO;

@Component
public class FeeDAO extends GenericDAO<Fee> implements InterfaceFeeDAO {

	public FeeDAO() {
		super();
	}

	@Override
	protected Fee mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		Fee feeRecord = new Fee();
		feeRecord.setStudentId(resultSet.getString("studentId")); // Replace with actual column name
		feeRecord.setFeeAmount(resultSet.getDouble("feeAmount")); // Replace with actual column name
		feeRecord.setDeadline(resultSet.getDate("deadline").toLocalDate());
		feeRecord.setFine(resultSet.getDouble("fine"));
		return feeRecord;
	}

	@Override
	public Fee checkFees(String studentId) throws SQLException, ClassNotFoundException {
		String selectSQL = String.format("SELECT * FROM Fees WHERE studentId = '%s'", studentId);
		System.out.println(selectSQL);
		Fee fee = executeGetQuery(selectSQL);
		return fee;
	}

	@Override
	public boolean payFees(String studentId) throws SQLException, ClassNotFoundException {
		String updateSQL = String.format("UPDATE Fees SET feeAmount = 0, fine = 0 WHERE studentId = '%s'", studentId);
		return executeQuery(updateSQL);
	}

	@Override
	public boolean insertFees(Fee fee) throws ClassNotFoundException, SQLException {
		String addSQL = String.format(
				"INSERT INTO Fees (studentId, feeAmount, deadline, fine) VALUES ('%s', '%s', '%s', '%s')",
				fee.getStudentId(), fee.getFeeAmount(), fee.getDeadline(), fee.getFine());
		System.out.println(addSQL);
		boolean flag = executeQuery(addSQL);
		return flag;
	}

	@Override
	public boolean updateFees(Fee fee) throws ClassNotFoundException, SQLException {
		String updateSQL = String.format(
				"UPDATE Fees SET feeAmount = '%s', deadline = '%s', fine = '%s' WHERE studentId = '%s'",
				fee.getFeeAmount(), fee.getDeadline(), fee.getFine(), fee.getStudentId());
		boolean flag = executeQuery(updateSQL);
		return flag;
	}

	@Override
	public Fee calculateFine(String studentId) throws SQLException, ClassNotFoundException {
		String selectSQL = String.format("SELECT * FROM Fees WHERE studentId = '%s'", studentId);
		Fee fee = executeGetQuery(selectSQL);
		return fee;
	}

	@Override
	public void addFees(Fee fee) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean addTransaction(Transactions transaction) throws ClassNotFoundException, SQLException {
		String updateSQL = String.format(
				"Insert into Transactions (studentId ,feeAmount,notes) values ('%s', '%s', '%s') ",
				transaction.getStudentId(), transaction.getFeeAmount(), transaction.getNotes());
		boolean flag = executeQuery(updateSQL);
		return flag;
	}

}