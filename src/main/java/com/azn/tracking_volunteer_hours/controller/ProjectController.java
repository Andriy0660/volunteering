package com.azn.tracking_volunteer_hours.controller;

import com.azn.tracking_volunteer_hours.entity.Project;
import com.azn.tracking_volunteer_hours.entity.User;
import com.azn.tracking_volunteer_hours.entity.UserDetailsImpl;
import com.azn.tracking_volunteer_hours.exception.BadRequestException;
import com.azn.tracking_volunteer_hours.service.ProjectService;
import com.azn.tracking_volunteer_hours.service.UserProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final UserProjectsService userProjectService;
    @GetMapping()
    public ResponseEntity<List<Project>> getProjects(@RequestParam boolean isFuture) {

        if(isFuture) {
            return ResponseEntity.ok(projectService.findAllByStartTimeIsAfter(LocalDateTime.now()));
        }
        return ResponseEntity.ok(projectService.findAllByStartTimeIsBefore(LocalDateTime.now()));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Project>> filter(@RequestParam("category")String category,
                                          @RequestParam("period")Integer period){

        if(period<1||period>12){
            throw new BadRequestException("Count of months must be between 1 and 12");
        }
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        List<Project> projects = null;
        if(period==0){
            projects=userProjectService.getProjectsByUserId(user.getId()).stream().
                    map(i->projectService.findById(i.getProject_id()).orElseThrow()).toList();
        }
        else {
            projects = userProjectService.getProjectsAfterDateByUserId(LocalDateTime.now().minusMonths(period)
                    , user.getId()).stream().
                    map(i -> projectService.findById(i.getProject_id()).orElseThrow()).
                    toList();
        }


        if(category.equals("All")){
            return ResponseEntity.ok(projects);
        }
        return ResponseEntity.ok(projects.stream().filter(i->i.getCategory().equals(category)).toList());

    }

    @GetMapping("/report")
    public ResponseEntity<List<Project>> report(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        User user = userDetails.getUser();

        return ResponseEntity.ok(userProjectService.getProjectsByUserId(user.getId()).stream().
                map(i -> projectService.findById(i.getProject_id()).
                        orElseThrow()).toList());
    }


}
