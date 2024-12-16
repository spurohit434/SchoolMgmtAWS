package com.wg.model;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fee {
	String studentId;
	@NotNull(message = "fee amount can not be null")
	@Min(value = 0, message = "fee amount can not be negative")
	@Max(value = 10000, message = "fee amount should be less than 10000")
	double feeAmount;
	LocalDate deadline;
	@Min(value = 0, message = "fee fine can not be negative")
	double fine;

	public String getStudentId() {
		return studentId;
	}

	public Fee(String studentId, double feeAmount, LocalDate deadline, double fine) {
		super();
		this.studentId = studentId;
		this.feeAmount = feeAmount;
		this.deadline = deadline;
		this.fine = fine;
	}

	public Fee() {
	}
}
