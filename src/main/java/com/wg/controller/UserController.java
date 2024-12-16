package com.wg.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wg.dto.ApiResponseHandler;
import com.wg.dto.PaginatedResponse;
import com.wg.dto.UserDto;
import com.wg.helper.PasswordUtil;
import com.wg.mapper.UserMapper;
import com.wg.model.StatusResponse;
import com.wg.model.User;
import com.wg.services.interfaces.InterfaceUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

	private InterfaceUserService userService;

	@Autowired
	public UserController(InterfaceUserService userService) {
		this.userService = userService;
	}

	@PostMapping("/user")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		PasswordUtil passwordUtil = new PasswordUtil();
		String hashedPassword = passwordUtil.hashPassword(user.getPassword());
		user.setPassword(hashedPassword);
		boolean flag = userService.addUser(user);
		if (flag == true) {
			return ApiResponseHandler.apiResponseHandler("User Created Successfully", StatusResponse.Success,
					HttpStatus.CREATED, user);
		}
		return ApiResponseHandler.apiResponseHandler("User can not be created", StatusResponse.Error,
				HttpStatus.BAD_REQUEST, null);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable String id) {
		User user = userService.getUserById(id);
		UserDto dto = UserMapper.mapUser(user);
		return ApiResponseHandler.apiResponseHandler("User Fetched Successfully", StatusResponse.Success, HttpStatus.OK,
				dto); // 200 OK with the user as the body
	}

	@GetMapping("/class/{standard}")
	public ResponseEntity<Object> getClassDetails(@PathVariable int standard) {
		List<User> users = userService.getClassDetails(standard);
		List<UserDto> dtos = new ArrayList<>();
		for (User user : users) {
			UserDto dto = UserMapper.mapUser(user);
			dtos.add(dto);
		}
		return ApiResponseHandler.apiResponseHandler("Standard Fetched Successfully", StatusResponse.Success,
				HttpStatus.OK, users);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {
		Boolean flag = userService.deleteUser(id);
		if (flag == true) {
			return ApiResponseHandler.apiResponseHandler("User Deleted Successfully", StatusResponse.Success,
					HttpStatus.OK, null);
		}
		return ApiResponseHandler.apiResponseHandler("User can not be Deleted", StatusResponse.Error,
				HttpStatus.BAD_REQUEST, null);
	}

	@GetMapping("/users")
	public ResponseEntity<Object> getAllUser(@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam(defaultValue = "10", required = false) int size,
			@RequestHeader("Authorization") String token) {
		// Fetch paginated user list from the service layer
		List<User> users = userService.getAllUser(page, size);
		// Get the total number of users (for pagination)
		int totalElements = userService.getTotalUserCount();
		// map all the users to the DTO
		List<UserDto> dtos = new ArrayList<>();
		for (User user : users) {
			UserDto dto = UserMapper.mapUser(user);
			dtos.add(dto);
		}
		// Create PaginatedResponse object
		PaginatedResponse<UserDto> paginatedResponse = new PaginatedResponse<>(dtos, page, size, totalElements);
		// Return the paginated response
		return ApiResponseHandler.apiResponseHandler("User Fetched Successfully", StatusResponse.Success, HttpStatus.OK,
				paginatedResponse);
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable String id, @RequestBody User updatedUser) {
		PasswordUtil passwordUtil = new PasswordUtil();
		String hashedPassword = passwordUtil.hashPassword(updatedUser.getPassword());
		updatedUser.setPassword(hashedPassword);
		boolean flag = userService.updateUser(id, updatedUser);
		if (flag == true) {
			return ApiResponseHandler.apiResponseHandler("User Updated Successfully", StatusResponse.Success,
					HttpStatus.OK, null);
		}
		return ApiResponseHandler.apiResponseHandler("User can not be updated", StatusResponse.Error,
				HttpStatus.BAD_REQUEST, null);
	}
}