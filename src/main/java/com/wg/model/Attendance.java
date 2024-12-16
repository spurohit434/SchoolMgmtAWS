package com.wg.model;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Attendance {
	@NotNull(message = "StudentId must not be null")
	private String studentId;
	@NotNull(message = "Standard must not be null")
	@Min(value = 1, message = "standard should be greater than 1")
	@Max(value = 12, message = "standard should be ")
	private int standard;
	@NotNull(message = "Date must not be null")
	@Past
	private LocalDate date;
	@NotNull(message = "Attendance status must not be null")
	private Status status;

	public Attendance() {
	}

	public Attendance(String studentId, int standard, LocalDate date, Status status) {
		this.studentId = studentId;
		this.standard = standard;
		this.date = date;
		this.status = status;
	}
}