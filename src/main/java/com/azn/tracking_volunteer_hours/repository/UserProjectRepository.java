package com.azn.tracking_volunteer_hours.repository;

import com.azn.tracking_volunteer_hours.entity.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserProjectRepository extends JpaRepository<UserProject,Long> {
    //List<UserProject> getProjectsAfterDateByUserId();
    List<UserProject> findAllByDateAfterAndUserId(LocalDateTime time,Long id);
    List<UserProject> getProjectsByUserId(Long id);




}
