package com.azn.tracking_volunteer_hours.repository;

import com.azn.tracking_volunteer_hours.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompaniesRepository extends JpaRepository<Company,Long> {
    Optional<Company> findById(Long id);
}
