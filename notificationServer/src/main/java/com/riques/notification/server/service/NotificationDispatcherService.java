package com.riques.notification.server.service;

import com.riques.notification.server.model.NotificationLog;
import com.riques.notification.server.model.User;
import com.riques.notification.server.repository.NotificationLogRepository;
import com.riques.notification.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationDispatcherService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationLogRepository notificationLogRepository;

    @Autowired
    private NotificationServiceFactory notificationServiceFactory;

    public void sendNotification(String category, String message) {
        List<User> users = userRepository.findAll();
        users.stream().filter(user -> user.getSubscribedCategories().contains(category)).forEach(user -> {
            user.getNotificationChannels().forEach(channel -> {
                NotificationService notificationService = notificationServiceFactory.getNotificationService(channel);
                notificationService.sendNotification(user, message);
                logNotification(user, category, channel);
            });
        });
    }

    private void logNotification(User user, String messageType, String notificationType) {
        NotificationLog log = new NotificationLog();
        log.setUserId(user.getId());
        log.setUserName(user.getName());
        log.setMessageType(messageType);
        log.setNotificationType(notificationType);
        log.setTimestamp(LocalDateTime.now());
        notificationLogRepository.save(log);
    }

    public List<NotificationLog> getLogs() {
        return notificationLogRepository.findAll().reversed();
    }
}
