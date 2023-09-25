package com.example.travelseeker.repository;

import com.example.travelseeker.model.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
