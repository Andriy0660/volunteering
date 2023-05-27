package com.azn.tracking_volunteer_hours.repository;

import com.azn.tracking_volunteer_hours.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OffersRepository extends JpaRepository<Offer,Long> {
    Optional<Offer> findById(Long id);
}
