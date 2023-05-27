package com.azn.tracking_volunteer_hours.repository;

import com.azn.tracking_volunteer_hours.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {

}
