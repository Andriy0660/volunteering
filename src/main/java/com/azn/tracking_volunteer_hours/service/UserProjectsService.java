package com.azn.tracking_volunteer_hours.service;

import com.azn.tracking_volunteer_hours.entity.UserProject;
import com.azn.tracking_volunteer_hours.repository.UserProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserProjectsService {
    private final UserProjectRepository userProjectRepository;
    public List<UserProject> getProjectsAfterDateByUserId(LocalDateTime time, Long id){
        return userProjectRepository.findAllByDateAfterAndUserId(time,id);
    }
    public List<UserProject> getProjectsByUserId(Long id){
        return userProjectRepository.getProjectsByUserId(id);
    }

    public void save(UserProject userProject){
        userProjectRepository.save(userProject);
    }




}
