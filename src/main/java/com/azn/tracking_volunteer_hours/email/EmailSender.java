package com.azn.tracking_volunteer_hours.email;
public interface EmailSender {
    void send(String to, String email);
}