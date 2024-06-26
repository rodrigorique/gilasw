package com.riques.notification.server.service;

public interface NotificationServiceFactory {
    NotificationService getNotificationService(String channel);
}

