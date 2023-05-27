package com.azn.tracking_volunteer_hours.controller;

import com.azn.tracking_volunteer_hours.entity.User;
import com.azn.tracking_volunteer_hours.entity.UserDetailsImpl;
import com.azn.tracking_volunteer_hours.entity.UserProject;
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
    private final UserProjectsService service;
    @PostMapping("/contributeHours")
    public ResponseEntity<Void> contributeHours(
            @RequestParam int hours,
            @RequestParam Long project_id
    ){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        User user = userDetails.getUser();

        UserProject userProject = UserProject.builder()
                .date(LocalDateTime.now())
                .hours(hours)
                .user_id(user.getId())
                .project_id(project_id)
                .build();
        service.save(userProject);

        return ResponseEntity.ok().build();
    }
}
