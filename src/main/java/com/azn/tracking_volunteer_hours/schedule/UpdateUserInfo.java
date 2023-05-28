package com.azn.tracking_volunteer_hours.schedule;

import com.azn.tracking_volunteer_hours.email.BuildEmailMessage;
import com.azn.tracking_volunteer_hours.email.gold_heart.EmailSender;
import com.azn.tracking_volunteer_hours.entity.Project;
import com.azn.tracking_volunteer_hours.entity.User;
import com.azn.tracking_volunteer_hours.service.ProjectService;
import com.azn.tracking_volunteer_hours.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateUserInfo {
    private final UserService userService;
    private final EmailSender emailSenderGoldHeart;
    private final ProjectService projectService;
    private final com.azn.tracking_volunteer_hours.email.notification.EmailSender emailSender;
    @Scheduled(fixedRate = 6000)
    public void updateUserRank(){
        List<User> users = userService.findAll();
        for(User user: users) {
            if (user.getHours() > 2000 && !user.isGotGoldHeart()) {
                emailSenderGoldHeart.send(
                        user.getEmail(),
                        BuildEmailMessage.buildEmailHonors(user.getLastname() + " " + user.getFirstname()));
                user.setGotGoldHeart(true);
            }
        }
    }
    @Scheduled(fixedRate = 6000)
    public void sendNotification(){
            List<Project> projects = projectService.findAllBySentEmailFalse();
        for(Project project: projects) {
            if (project.getStartTime().isBefore(LocalDateTime.now().plusDays(7)) && project.getStartTime().isAfter(LocalDateTime.now())) {
                List<User> users= userService.findAll();
                for(User user : users) {
                    emailSender.send(
                            user.getEmail(),
                            BuildEmailMessage.buildEmailNotification(project));
                }
                project.setSentEmail(true);
                projectService.save(project);
            }
        }
    }


}
