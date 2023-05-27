package com.azn.tracking_volunteer_hours.email.ticket;
public interface EmailSender {
    void send(String to, String content);
}