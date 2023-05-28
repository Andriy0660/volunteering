package com.azn.tracking_volunteer_hours.schedule;

import com.azn.tracking_volunteer_hours.dto.response.ProjectResponse;
import com.azn.tracking_volunteer_hours.dto.response.ReportResponse;
import com.azn.tracking_volunteer_hours.email.BuildEmailMessage;
import com.azn.tracking_volunteer_hours.email.gold_heart.EmailSender;
import com.azn.tracking_volunteer_hours.entity.Project;
import com.azn.tracking_volunteer_hours.entity.User;
import com.azn.tracking_volunteer_hours.entity.UserProject;
import com.azn.tracking_volunteer_hours.service.ProjectService;
import com.azn.tracking_volunteer_hours.service.UserProjectsService;
import com.azn.tracking_volunteer_hours.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateUserInfo {
    private final UserService userService;
    private final EmailSender emailSenderGoldHeart;
    private final com.azn.tracking_volunteer_hours.email.report.EmailSender emailReportSender;
    private final ProjectService projectService;
    private final UserProjectsService userProjectsService;
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
    @Scheduled(fixedRate = 60000)
    public void sendReport(){
        List<User> users = userService.findAll();
        for(User user: users) {
            List<UserProject> userProjects = userProjectsService.findAllByDateAfterAndUserId(
                    LocalDateTime.now().minusDays(7), user.getId());

            List<Project> projects = userProjects.stream()
                    .map(i -> projectService.findById(i.getProjectId()).orElseThrow()).toList();

            ReportResponse reportResponse = new ReportResponse();
            List<ProjectResponse> projectResponses = new ArrayList<>();
            for (int i = 0; i < userProjects.size(); i++) {
                projectResponses.add(new ProjectResponse(
                        projects.get(i), userProjects.get(i).getDate(), userProjects.get(i).getHours()));
            }
            Integer hours = userProjects.stream().map(i -> i.getHours()).mapToInt(Integer::intValue).sum();
            reportResponse.setProjectResponses(projectResponses);
            reportResponse.setAllHours(hours);
            emailReportSender.send(user.getEmail(), BuildEmailMessage.buildEmailReport(reportResponse));
        }
    }
}
