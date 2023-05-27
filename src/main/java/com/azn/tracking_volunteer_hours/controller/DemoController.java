package com.azn.tracking_volunteer_hours.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/")
    public String getMessage(){
        return "Tracking volunteer hours is running:) ";
    }
}
