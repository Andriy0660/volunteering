package com.azn.tracking_volunteer_hours.service;

import com.azn.tracking_volunteer_hours.entity.UserProject;
import com.azn.tracking_volunteer_hours.repository.UserProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserProjectsService {
    private final UserProjectRepository repository;

    public void save(UserProject userProject){
        repository.save(userProject);
    }

}
