package com.wg.repository.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.wg.model.User;

public interface InterfaceUserDAO {

	public boolean addUser(User user) throws SQLException, ClassNotFoundException;

	public User getUserById(String userId) throws SQLException, ClassNotFoundException;

	public User getUserByUsername(String username) throws SQLException, ClassNotFoundException;

	public List<User> getClassDetails(int standard) throws SQLException, ClassNotFoundException;

	public boolean deleteUser(String id) throws SQLException, ClassNotFoundException;

//	public boolean updateUser(User user, String userId, String columnToUpdate)
//			throws SQLException, ClassNotFoundException;

	boolean updateUser(String userId, User user);

	public List<User> getAllUser(int pageNumber, int pageSize) throws SQLException, ClassNotFoundException;

	public int getTotalUserCount() throws ClassNotFoundException, SQLException;
}