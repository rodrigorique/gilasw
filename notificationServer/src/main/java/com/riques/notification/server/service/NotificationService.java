package com.riques.notification.server.service;

import com.riques.notification.server.model.User;

public interface NotificationService {
    void sendNotification(User user, String message);
}
