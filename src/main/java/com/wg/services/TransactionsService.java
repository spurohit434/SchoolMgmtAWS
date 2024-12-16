package com.wg.services;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wg.helper.LoggingUtil;
import com.wg.model.Transactions;
import com.wg.repository.interfaces.InterfaceTransactionsDAO;

@Service
public class TransactionsService {

	private InterfaceTransactionsDAO transactionsDAO;
	Logger logger = LoggingUtil.getLogger(FeeService.class);

	public TransactionsService() {
	}

	@Autowired
	public TransactionsService(InterfaceTransactionsDAO transactionsDAO) {
		this.transactionsDAO = transactionsDAO;
	}

	public List<Transactions> getTransaction(String id) {
		try {
			return transactionsDAO.getTransactions(id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
