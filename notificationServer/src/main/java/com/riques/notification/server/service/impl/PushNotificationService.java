package com.riques.notification.server.service.impl;

import com.riques.notification.server.model.User;
import com.riques.notification.server.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationService implements NotificationService {

    @Override
    public void sendNotification(User user, String message) {
        System.out.println("Sending Push Notification to " + user.getName() + ": " + message);
    }
}
