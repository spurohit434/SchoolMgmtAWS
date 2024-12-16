package com.wg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wg.model.Role;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserDto {
	private String userId;
	private String name;
	private String email;
	private String username;
	private int age;
	private String address;
	private Role role;
	private String gender;
}