package com.wg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wg.dto.ApiResponseHandler;
import com.wg.model.Notification;
import com.wg.model.StatusResponse;
import com.wg.services.NotificationService;

@RestController
@RequestMapping("/api")
public class NotificationController {

	private NotificationService notificationService;

	public NotificationController() {
	}

	@Autowired
	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@PostMapping("/user/{id}/notification")
	public ResponseEntity<Object> sendNotification(@RequestBody Notification notification) {
		notificationService.sendNotification(notification);
		return ApiResponseHandler.apiResponseHandler("Notification created and send successfully",
				StatusResponse.Success, HttpStatus.CREATED, notification);
	}

	@GetMapping("/user/{id}/notification")
	public ResponseEntity<Object> readNotifications(@PathVariable String id) {
		List<Notification> lists = notificationService.readNotifications(id);
		return ApiResponseHandler.apiResponseHandler("Notification fetched successfully", StatusResponse.Success,
				HttpStatus.OK, lists);
	}
}