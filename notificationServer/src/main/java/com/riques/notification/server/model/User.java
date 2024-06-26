package com.riques.notification.server.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;

    @ElementCollection
    private List<String> subscribedCategories;

    @ElementCollection
    private List<String> notificationChannels;

    public User() {
    }

    public User(String name, String email, String phoneNumber, List<String> subscribedCategories, List<String> notificationChannels) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.subscribedCategories = subscribedCategories;
        this.notificationChannels = notificationChannels;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getSubscribedCategories() {
        return subscribedCategories;
    }

    public void setSubscribedCategories(List<String> subscribedCategories) {
        this.subscribedCategories = subscribedCategories;
    }

    public List<String> getNotificationChannels() {
        return notificationChannels;
    }

    public void setNotificationChannels(List<String> notificationChannels) {
        this.notificationChannels = notificationChannels;
    }
}
