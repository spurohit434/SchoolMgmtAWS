package com.wg.services.interfaces;

import java.util.List;

import com.wg.model.User;

public interface InterfaceUserService {

	boolean addUser(User user);

	User getUserById(String userId);

	List<User> getClassDetails(int standard);

	boolean deleteUser(String id);

	int getTotalUserCount();

	List<User> getAllUser(int pageNumber, int pageSize);

	User getUserByUsername(String username);

	boolean updateUser(String userId, User user);

// 	StringBuilder s1;
}