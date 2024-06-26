package com.riques.notification.server.service.impl;

import com.riques.notification.server.service.NotificationService;
import com.riques.notification.server.service.NotificationServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationServiceFactoryImpl implements NotificationServiceFactory {

    @Autowired
    private SMSNotificationService smsNotificationService;

    @Autowired
    private EmailNotificationService emailNotificationService;

    @Autowired
    private PushNotificationService pushNotificationService;

    @Override
    public NotificationService getNotificationService(String channel) {
        return switch (channel) {
            case "SMS" -> smsNotificationService;
            case "E-Mail" -> emailNotificationService;
            case "Push Notification" -> pushNotificationService;
            default -> throw new IllegalArgumentException("Unsupported notification channel: " + channel);
        };
    }
}
