package com.example.travelseeker.repository;

import com.example.travelseeker.model.entities.BoughtOffers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BoughtOffersRepository extends JpaRepository<BoughtOffers, UUID> {
}
