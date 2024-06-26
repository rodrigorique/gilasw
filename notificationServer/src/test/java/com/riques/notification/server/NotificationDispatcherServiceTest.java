package com.riques.notification.server;

import com.riques.notification.server.model.NotificationLog;
import com.riques.notification.server.model.User;
import com.riques.notification.server.repository.NotificationLogRepository;
import com.riques.notification.server.repository.UserRepository;
import com.riques.notification.server.service.NotificationDispatcherService;
import com.riques.notification.server.service.NotificationService;
import com.riques.notification.server.service.NotificationServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class NotificationDispatcherServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private NotificationLogRepository notificationLogRepository;

    @Mock
    private NotificationServiceFactory notificationServiceFactory;

    @InjectMocks
    private NotificationDispatcherService notificationDispatcherService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendNotification() {

        User user1 = new User("Test User 1", "test@test.com", "1234567890", List.of("Sports"), List.of("SMS"));

        when(userRepository.findAll()).thenReturn(Collections.singletonList(user1));

        NotificationService mockNotificationService = mock(NotificationService.class);
        when(notificationServiceFactory.getNotificationService(anyString())).thenReturn(mockNotificationService);

        notificationDispatcherService.sendNotification("Sports", "Test message");

        verify(mockNotificationService, times(1)).sendNotification(eq(user1), eq("Test message"));
        verify(notificationLogRepository, times(1)).save(any(NotificationLog.class));
    }

    @Test
    public void testLogNotification() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        User user = new User("Test User", "test@test.com", "1234567890", List.of("Sports"), List.of("SMS"));

        Method method = NotificationDispatcherService.class.getDeclaredMethod("logNotification", User.class, String.class, String.class);
        method.setAccessible(true);
        method.invoke(notificationDispatcherService, user, "Sports", "SMS");

        ArgumentCaptor<NotificationLog> captor = ArgumentCaptor.forClass(NotificationLog.class);
        verify(notificationLogRepository, times(1)).save(captor.capture());

        NotificationLog savedLog = captor.getValue();
        assertEquals("Test User", savedLog.getUserName());
        assertEquals("Sports", savedLog.getMessageType());
        assertEquals("SMS", savedLog.getNotificationType());
        assertNotNull(savedLog.getTimestamp());
    }

    @Test
    public void testGetLogs() {
        List<NotificationLog> mockLogs = Arrays.asList(
                new NotificationLog("Sports", "SMS", 1L, "Test User", LocalDateTime.now()),
                new NotificationLog("Movies", "SMS", 1L, "Test User", LocalDateTime.now())
        );

        when(notificationLogRepository.findAll()).thenReturn(mockLogs);

        List<NotificationLog> logs = notificationDispatcherService.getLogs();

        assertEquals(mockLogs.size(), logs.size());
        assertEquals(mockLogs.get(0), logs.get(logs.size() - 1));
    }
}


