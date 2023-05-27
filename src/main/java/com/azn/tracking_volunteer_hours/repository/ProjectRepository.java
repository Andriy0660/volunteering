package com.azn.tracking_volunteer_hours.repository;

import com.azn.tracking_volunteer_hours.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {
public List<Project> findAllByStartTimeIsBefore(LocalDateTime now);
public List<Project> findAllByStartTimeIsAfter(LocalDateTime now);
}
