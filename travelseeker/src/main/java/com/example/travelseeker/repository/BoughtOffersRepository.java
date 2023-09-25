package com.example.travelseeker.repository;

import com.example.travelseeker.model.entities.BoughtOffers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoughtOffersRepository extends JpaRepository<BoughtOffers, Long> {
}
