package com.example.travelseeker.repository;

import com.example.travelseeker.model.entities.CarRent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRentRepository extends JpaRepository<CarRent, UUID> {
    CarRent findCarRentById(UUID id);

    //CarRent getAllBySellerId(Long id);
}
