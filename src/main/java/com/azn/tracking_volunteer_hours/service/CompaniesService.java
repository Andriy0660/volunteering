package com.azn.tracking_volunteer_hours.service;

import com.azn.tracking_volunteer_hours.entity.Company;
import com.azn.tracking_volunteer_hours.repository.CompaniesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CompaniesService {
    private final CompaniesRepository companiesRepository;

    public List<Company> findAll() {
        return companiesRepository.findAll();
    }
    public Optional<Company> findById(Long id){
        return companiesRepository.findById(id);
    }


}
