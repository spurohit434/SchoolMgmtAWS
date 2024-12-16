package com.wg.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wg.model.Transactions;
import com.wg.repository.interfaces.InterfaceTransactionsDAO;

@Component
public class TransactionsDAO extends GenericDAO<Transactions> implements InterfaceTransactionsDAO {
	public TransactionsDAO() {
		super();
	}

	@Override
	protected Transactions mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		Transactions transaction = new Transactions();
		transaction.setStudentId(resultSet.getString("studentId")); // Replace with actual column name
		transaction.setFeeAmount(resultSet.getDouble("feeAmount")); // Replace with actual column name
		transaction.setNotes(resultSet.getString("notes"));
		transaction.setPaymentDate(resultSet.getDate("paymentDate").toLocalDate());
		transaction.setTransactionId(resultSet.getString("transactionId"));
		return transaction;
	}

	@Override
	public List<Transactions> getTransactions(String id) throws ClassNotFoundException, SQLException {
		String executeSQL = String.format("Select * from Transactions where studentId = '%s'", id);
		List<Transactions> transactions = executeGetAllQuery(executeSQL);
		return transactions;
	}

}
