package com.azn.tracking_volunteer_hours.schedule;

import com.azn.tracking_volunteer_hours.email.BuildEmailMessage;
import com.azn.tracking_volunteer_hours.email.gold_heart.EmailSender;
import com.azn.tracking_volunteer_hours.entity.User;
import com.azn.tracking_volunteer_hours.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateUserInfo {
    private final UserService userService;
    private final EmailSender emailSender;
    @Scheduled(fixedRate = 6000)
    public void updateUserRank(){
        List<User> users = userService.findAll();
        for(User user: users) {
            if (user.getHours() > 2000 && !user.isGotGoldHeart()) {
                emailSender.send(
                        user.getEmail(),
                        BuildEmailMessage.buildEmailHonors(user.getLastname() + " " + user.getFirstname()));
                user.setGotGoldHeart(true);
            }
        }
    }
}
