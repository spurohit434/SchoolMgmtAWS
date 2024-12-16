package com.wg.repository.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.wg.model.Transactions;

public interface InterfaceTransactionsDAO {

	List<Transactions> getTransactions(String id) throws ClassNotFoundException, SQLException;
}
