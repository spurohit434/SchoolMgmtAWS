package com.wg.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Issue {

	private String issueID;
	@NotNull(message = "Issue message can not be null")
	private String message;
	// @NotNull(message = "User Id can not be null")
	private String userId;
	@NotNull(message = "Issue status can not be null")
	private IssuesStatus status;
	private LocalDate createdAt;

	public Issue() {
	}

	public Issue(String issueID, String message, String userId, IssuesStatus status) {
		this.issueID = issueID;
		this.message = message;
		this.userId = userId;
		this.status = status;
	}
}
