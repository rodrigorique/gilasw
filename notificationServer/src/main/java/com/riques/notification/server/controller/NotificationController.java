package com.riques.notification.server.controller;

import com.riques.notification.server.model.NotificationLog;
import com.riques.notification.server.model.NotificationRequest;
import com.riques.notification.server.service.NotificationDispatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationDispatcherService notificationDispatcherService;

    @PostMapping("/send")
    public void sendNotification(@RequestBody NotificationRequest request) {
        notificationDispatcherService.sendNotification(request.getCategory(), request.getMessage());
    }

    @GetMapping("/logs")
    public List<NotificationLog> getLogs() {
        return notificationDispatcherService.getLogs();
    }
}
