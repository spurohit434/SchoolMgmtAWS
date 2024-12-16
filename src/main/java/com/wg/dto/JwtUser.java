package com.wg.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtUser {
	@NotNull(message = "username can not be null")
	String username;
	@NotNull(message = "password can not be null")
	String password;
}
