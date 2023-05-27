package com.azn.tracking_volunteer_hours.email.gold_heart;
public interface EmailSender {
    void send(String to, String content);
}