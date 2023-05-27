package com.azn.tracking_volunteer_hours.service;

import com.azn.tracking_volunteer_hours.entity.Offer;
import com.azn.tracking_volunteer_hours.repository.OffersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OffersService {
    private final OffersRepository offersRepository;
    public Optional<Offer> findById(Long id){
        return offersRepository.findById(id);
    }


}
