package com.azn.tracking_volunteer_hours.repository;

import com.azn.tracking_volunteer_hours.entity.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserProjectRepository extends JpaRepository<UserProject,Long> {
    public List<UserProject> findAllByDateAfterAndUser_idIs(LocalDateTime time,Long id);

}
