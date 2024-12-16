package com.wg.services;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wg.model.Message;
import com.wg.model.Notification;
import com.wg.model.User;
import com.wg.repository.NotificationDAO;
import com.wg.repository.interfaces.InterfaceNotificationDAO;
import com.wg.services.interfaces.InterfaceUserService;

@Service
public class NotificationService {
	private InterfaceNotificationDAO notificationDAO = new NotificationDAO();
	private final SQSMessagePublisher sqsMessagePublisher;
	private InterfaceUserService userService;

//	public NotificationService() {
//	}

	@Autowired
	public NotificationService(InterfaceNotificationDAO notificationDAO, InterfaceUserService userService,
			SQSMessagePublisher sqsMessagePublisher) {
		this.sqsMessagePublisher = sqsMessagePublisher;
		this.notificationDAO = notificationDAO;
		this.userService = userService;
	}

	public boolean sendNotification(Notification notification) {
		boolean sendStatus = false;
		try {
			String randomString = UUID.randomUUID().toString();
			int desiredLength = 7;
			if (desiredLength > randomString.length()) {
				desiredLength = randomString.length();
			}
			String notificationId = randomString.substring(0, desiredLength);
			notificationId = 'L' + notificationId;
			notification.setNotificationId(notificationId);
			LocalDate date = LocalDate.now();
			notification.setDateIssued(date);
			sendStatus = notificationDAO.sendNotification(notification);
		//	return sendStatus;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		User user = userService.getUserById(notification.getUserId());
		Message message = new Message(notification.getNotificationId(), notification.getDescription(),
				notification.getType(), user.getEmail(), LocalDateTime.now());
		sqsMessagePublisher.publishMessage(message);
		return sendStatus;
	}

	public List<Notification> readNotifications(String userId) {
		List<Notification> notificationList = null;
		try {
			notificationList = notificationDAO.readNotifications(userId);
			return notificationList;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return notificationList;
	}
}
