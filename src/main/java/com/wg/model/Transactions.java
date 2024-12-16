package com.wg.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transactions {
	@NotNull(message = "TransactionId must not be null")
	private String transactionId;

	@NotNull(message = "StudentId must not be null")
	private String studentId;

	@NotNull(message = "Payment amount must not be null")
	@Positive(message = "Payment amount must be greater than zero")
	private double feeAmount;

	@NotNull(message = "Payment date must not be null")
	private LocalDate paymentDate;

	private String notes; // Optional field for additional information

	public Transactions() {
	}

	public Transactions(String transactionId, String studentId, double feeAmount, LocalDate paymentDate, String notes) {
		this.transactionId = transactionId;
		this.studentId = studentId;
		this.feeAmount = feeAmount;
		this.paymentDate = paymentDate;
		this.notes = notes;
	}

	public Transactions(String userId, double totalFees, String notes2) {
		this.studentId = userId;
		this.feeAmount = totalFees;
		this.notes = notes2;
	}
}
