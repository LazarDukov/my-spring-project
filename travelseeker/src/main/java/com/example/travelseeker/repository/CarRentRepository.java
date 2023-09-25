package com.example.travelseeker.repository;

import com.example.travelseeker.model.entities.CarRent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRentRepository extends JpaRepository<CarRent, Long> {
}
