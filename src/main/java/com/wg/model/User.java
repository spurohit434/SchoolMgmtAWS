package com.wg.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	private String userId;
	@Size(min = 1, message = "Name must not be empty.")
	private String name;
	@Past
	private LocalDate dob;
	private String contactNumber;
	@NotNull
	private Role role;
	@NotNull
	private String password;
	private int standard;
	private String address;
	@NotBlank(message = "Username must not be null.")
	@Size(min = 4, message = "username should have atleast 4 characters")
	@NotNull
	private String username;
	@Min(value = 18, message = "Age must be at least 18.")
	@Max(value = 120, message = "Age must be less than 120.")
	private int age;
	@NotBlank(message = "Email must not be null.")
	@Email
	private String email;
	private String gender;
	private String rollNo;
	@JsonIgnore
	private List<Integer> assignedToStandard;
	private int mentorOf;

	public User() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDOB() {
		return dob;
	}

	public void setDOB(LocalDate dateOfBirth) {
		this.dob = dateOfBirth;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStandard() {
		return standard;
	}

	public void setStandard(int standard) {
		this.standard = standard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public int getMentorOf() {
		return mentorOf;
	}

	public void setMentorOf(int mentorOf) {
		this.mentorOf = mentorOf;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
