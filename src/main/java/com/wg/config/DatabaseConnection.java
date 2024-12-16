package com.wg.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static Connection connection;

	public static Connection getConnection() throws ClassNotFoundException {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection("jdbc:mysql://mydbinstance.cfkgcw26g00n.ap-south-1.rds.amazonaws.com:3306/school", "admin", "mysql123");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;


	}
}