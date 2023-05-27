package com.azn.tracking_volunteer_hours.controller;

import com.azn.tracking_volunteer_hours.dto.response.ProjectResponse;
import com.azn.tracking_volunteer_hours.dto.response.ReportResponse;
import com.azn.tracking_volunteer_hours.entity.Project;
import com.azn.tracking_volunteer_hours.entity.User;
import com.azn.tracking_volunteer_hours.entity.UserDetailsImpl;
import com.azn.tracking_volunteer_hours.entity.UserProject;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final UserProjectsService userProjectService;
    @GetMapping("/get")
    public ResponseEntity<List<Project>> getProjects() {
        return ResponseEntity.ok(projectService.findAllByStartTimeIsBefore(LocalDateTime.now()));
    }

    @GetMapping("/report")
    public ResponseEntity<ReportResponse> report(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        List<UserProject> userProjects= userProjectService.findAllByUserId(user.getId());
        List<ProjectResponse> projectResponses = new ArrayList<>();
        for(int i=0;i<userProjects.size();i++){
            projectResponses.add(new ProjectResponse(projectService.findById(userProjects.get(i).getProjectId()).orElseThrow(),
                    userProjects.get(i).getDate(),
                    userProjects.get(i).getHours()));
        }
        return ResponseEntity.ok(new ReportResponse(projectResponses,user.getHours()));
    }

    @GetMapping("/filterByCategory")
    public ResponseEntity<ReportResponse> filter(@RequestParam("category")String category){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        user.getUserProjects().stream().filter(i->i.getCategory().equals(category));
        List<UserProject> userProjects= userProjectService.findAllByUserId(user.getId());
       // List<Project> projects = userProjectService.findAllByUserId(user.getId()).

        List<ProjectResponse> projectResponses = new ArrayList<>();
        Integer hours=0;
        for(int i=0;i<userProjects.size();i++){

            if(projectService.findById(userProjects.get(i).getProjectId()).orElseThrow().getCategory().equals(category)){
                hours+=userProjects.get(i).getHours();
            projectResponses.add(new ProjectResponse(projectService.findById(userProjects.get(i).getProjectId()).orElseThrow(),
                    userProjects.get(i).getDate(),
                    userProjects.get(i).getHours()));
            }
        }
        return ResponseEntity.ok(new ReportResponse(projectResponses,hours));
    }


}
