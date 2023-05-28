package com.azn.tracking_volunteer_hours.email.notification;

public interface EmailSender {
    void send(String to, String content);
}