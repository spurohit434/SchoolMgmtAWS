package com.wg.model;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Leaves {
	String leaveId;
	String userId;
	@NotNull(message = "leave content can not be null")
	String content;
	@Future
	LocalDate startDate;
	@Future
	LocalDate endDate;
	LeavesStatus status;

	public Leaves(String leaveId, String userId, String content, LocalDate startDate, LocalDate endDate,
			LeavesStatus status) {
		super();
		this.leaveId = leaveId;
		this.userId = userId;
		this.content = content;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	public Leaves() {

	}
}