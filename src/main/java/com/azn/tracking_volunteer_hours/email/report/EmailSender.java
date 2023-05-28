package com.azn.tracking_volunteer_hours.email.report;

public interface EmailSender {
    void send(String to, String content);
}