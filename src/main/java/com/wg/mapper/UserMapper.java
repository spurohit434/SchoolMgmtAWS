package com.wg.mapper;

import com.wg.dto.UserDto;
import com.wg.model.User;

public class UserMapper {
	public static UserDto mapUser(User user) {
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setName(user.getName());
		userDto.setAge(user.getAge());
		userDto.setAddress(user.getAddress());
		userDto.setEmail(user.getEmail());
		userDto.setUsername(user.getUsername());
		userDto.setRole(user.getRole());
		userDto.setGender(user.getGender());
		return userDto;
	}
}