package com.azn.tracking_volunteer_hours.controller;

import com.azn.tracking_volunteer_hours.entity.Project;
import com.azn.tracking_volunteer_hours.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final ProjectService projectService;

    @GetMapping("/notifications")
    public List<Project> showFollowingProjects(){
        return projectService.findAllProjects()
                .stream()
                .filter((project ->
                        (project.getStartTime().isAfter(LocalDateTime.now())
                                &&
                                project.getStartTime().isBefore(LocalDateTime.now().plusDays(7)))))
                .collect(Collectors.toList());
    }
}
