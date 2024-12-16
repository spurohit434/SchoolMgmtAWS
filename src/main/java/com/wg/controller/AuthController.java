package com.wg.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wg.dto.ApiResponseHandler;
import com.wg.dto.JwtUser;
import com.wg.model.StatusResponse;
import com.wg.security.JwtUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

	private final UserDetailsService userDetailsService;

	private final AuthenticationManager authenticationManager;

	private final PasswordEncoder passwordEncoder;

	private final JwtUtil jwtUtil;

    @PostMapping("/authenticate")
	public ResponseEntity<Object> authenticateUser(@Valid @RequestBody JwtUser user) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername() , user.getPassword()));
		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
		String role = userDetails.getAuthorities().toString();
		role = role.substring(1, role.length() - 1).toUpperCase();
		String jwtToken = jwtUtil.generateToken(userDetails.getUsername(), role);
		return ApiResponseHandler.apiResponseHandler("Logged in successfully", StatusResponse.Success, HttpStatus.OK,
				Map.of("JWT Token", jwtToken));
	}

	@PostMapping("/user/logout")
	public ResponseEntity<Object> logoutUser(@RequestHeader("Authorization") String authorizationHeader) {
		// Check if the header is present and well-formed
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			return ApiResponseHandler.apiResponseHandler("Invalid token", StatusResponse.Error, HttpStatus.BAD_REQUEST,
					null);
		}
		// Extract the JWT token (removing "Bearer " prefix)
		String jwtToken = authorizationHeader.substring(7);
		// Add the token to the blacklist
		jwtUtil.blacklistToken(jwtToken);
		// Respond with success message
		return ApiResponseHandler.apiResponseHandler("Logged out successfully", StatusResponse.Success, HttpStatus.OK,
				null);
	}
}