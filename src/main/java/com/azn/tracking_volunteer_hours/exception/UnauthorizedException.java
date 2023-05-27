package com.azn.tracking_volunteer_hours.exception;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException() {
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}