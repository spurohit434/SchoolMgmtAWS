package com.wg.services;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wg.exceptions.DatabaseException;
import com.wg.exceptions.DuplicateException;
import com.wg.exceptions.GeneralException;
import com.wg.exceptions.InvalidInputException;
import com.wg.exceptions.NotFoundExceptions;
import com.wg.exceptions.StandardNotFoundException;
import com.wg.helper.LoggingUtil;
import com.wg.model.User;
import com.wg.repository.interfaces.InterfaceUserDAO;
import com.wg.services.interfaces.InterfaceUserService;

@Service
public class UserService implements InterfaceUserService {
	private InterfaceUserDAO userDAO;
	Logger logger = LoggingUtil.getLogger(UserService.class);

	public UserService() {
	}

	@Autowired
	public UserService(InterfaceUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public boolean addUser(User user) {
		boolean flag = false;
		try {
			String randomString = UUID.randomUUID().toString();
			int desiredLength = 7;
			if (desiredLength > randomString.length()) {
				desiredLength = randomString.length();
			}
			String userId = randomString.substring(0, desiredLength);
			userId = 'L' + userId;
			user.setUserId(userId);
			flag = userDAO.addUser(user);
		} catch (SQLException | ClassNotFoundException ex) {
			logger.severe(ex.getMessage());
			String message = ex.getMessage();
			System.out.println(message);
			if (message.contains("Duplicate entry")) {
				if (message.contains("username")) {
					throw new DuplicateException("Duplicate entry for Username " + user.getUsername());
				} else if (message.contains("email")) {
					throw new DuplicateException("Duplicate entry for Email " + user.getEmail());
				}
			}
			throw new DatabaseException("An error occurred while saving the user.");
		}
		return flag;
	}

	@Override
	public User getUserById(String userId) {
		User user = null;
		try {
			user = userDAO.getUserById(userId);
			if (user == null) {
				throw new NotFoundExceptions("User not found for id: " + userId);
			}
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
		}
		return user;
	}

	@Override
	public List<User> getClassDetails(int standard) {
		List<User> list = null;
		try {
			list = userDAO.getClassDetails(standard);
			if (list.isEmpty() || list == null) {
				throw new StandardNotFoundException("Students not found for standard " + standard);
			}
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
		}
		return list;
	}

	@Override
	public boolean deleteUser(String id) {
		User user = null;
		try {
			user = userDAO.getUserById(id);
			if (user == null) {
				throw new NotFoundExceptions("User not found for id: " + id);
			}
			if (user.getRole().toString().equalsIgnoreCase("Admin")) {
				return false;
			}
			return userDAO.deleteUser(id);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
		}
		return false;
	}

	@Override
	public int getTotalUserCount() {
		int count = 0;
		try {
			count = userDAO.getTotalUserCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<User> getAllUser(int pageNumber, int pageSize) {
		if (pageNumber < 0 || pageSize < 0) {
			throw new InvalidInputException(" PageNumber or Pagesize can not be negative");
		}
		List<User> users = null;
		try {
			// Pass pagination parameters (pageNumber and pageSize) to the DAO layer
			users = userDAO.getAllUser(pageNumber, pageSize);
			if (users == null || users.isEmpty()) {
				throw new GeneralException("Users does not exist");
			}
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User getUserByUsername(String username) {
		User user = null;
		try {
			user = userDAO.getUserByUsername(username);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean updateUser(String userId, User user) {
		User user1 = null;
		try {
			user1 = userDAO.getUserById(userId);
			if (user1 == null) {
				throw new NotFoundExceptions("User does not exist for userId " + userId);
			} else {
				return userDAO.updateUser(userId, user);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}