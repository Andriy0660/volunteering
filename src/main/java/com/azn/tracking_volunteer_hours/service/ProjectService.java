package com.azn.tracking_volunteer_hours.service;

import com.azn.tracking_volunteer_hours.entity.Project;
import com.azn.tracking_volunteer_hours.exception.NotFoundException;
import com.azn.tracking_volunteer_hours.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {
    private final ProjectRepository projectRepository;
    public List<Project> findAllByStartTimeIsBefore(LocalDateTime now){
        return projectRepository.findAllByStartTimeIsBefore(now);
    }
    public List<Project> findAllByStartTimeIsAfter(LocalDateTime now){
        return projectRepository.findAllByStartTimeIsAfter(now);
    }
   public  List<Project> findAllProjects(){
        return projectRepository.findAll();
    }
    public Project findProjectById(Long id){
        Optional<Project> project = projectRepository.findById(id);
        if(project.isEmpty()){
            throw new NotFoundException("Project with this id is not found");
        }
        return project.get();
    }
}
