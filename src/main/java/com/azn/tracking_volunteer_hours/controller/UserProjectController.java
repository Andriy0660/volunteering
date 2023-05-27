package com.azn.tracking_volunteer_hours.controller;

import com.azn.tracking_volunteer_hours.entity.Project;
import com.azn.tracking_volunteer_hours.entity.User;
import com.azn.tracking_volunteer_hours.entity.UserDetailsImpl;
import com.azn.tracking_volunteer_hours.entity.UserProject;
import com.azn.tracking_volunteer_hours.exception.BadRequestException;
import com.azn.tracking_volunteer_hours.service.ProjectService;
import com.azn.tracking_volunteer_hours.service.UserProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class UserProjectController {
    private final UserProjectsService userProjectService;
    private final ProjectService projectService;
    @PostMapping("/contributeHours")
    public ResponseEntity<Void> contributeHours(
            @RequestParam("hour") int hours,
            @RequestParam("project_id") Long project_id
    ){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        User user = userDetails.getUser();

        Project project = projectService.findById(project_id).orElseThrow(
                () -> new BadRequestException("This project doesn't exist")
        );

        if(project.getStartTime().isAfter(LocalDateTime.now())){
            throw new BadRequestException("This project didn't start yet");
        }

        UserProject userProject = UserProject.builder()
                .date(LocalDateTime.now())
                .hours(hours)
                .user_id(user.getId())
                .project_id(project_id)
                .build();
        userProjectService.save(userProject);

        return ResponseEntity.ok().build();
    }
}
