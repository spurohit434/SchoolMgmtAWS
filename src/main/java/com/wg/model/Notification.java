package com.wg.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notification {
	String notificationId;
	@NotNull(message = "User Id can not be null")
	String userId;
	@NotNull(message = "Notification description can not be null")
	String description;
	@NotNull(message = "Notification type can not be ull")
	String type;
	LocalDate dateIssued;

	public Notification(String notificationId, String userId, String description, String type, LocalDate dateIssued) {
		this.notificationId = notificationId;
		this.userId = userId;
		this.description = description;
		this.dateIssued = dateIssued;
		this.type = type;
	}

	public Notification() {
	}
}
