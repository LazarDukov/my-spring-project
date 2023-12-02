package com.example.travelseeker.repository;

import com.example.travelseeker.model.entities.CarRent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CarRentRepository extends JpaRepository<CarRent, UUID> {
    CarRent findCarRentById(UUID id);

    List<CarRent> findCarRentsBySellerIdAndAndAvailableGreaterThan(UUID id, int available);
}
