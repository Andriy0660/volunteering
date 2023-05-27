package com.azn.tracking_volunteer_hours.repository;

import com.azn.tracking_volunteer_hours.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findAllByStartTimeIsBefore(LocalDateTime now);
    List<Project> findAllByStartTimeIsAfter(LocalDateTime now);
    Optional<Project> findById(Long aLong);
}
