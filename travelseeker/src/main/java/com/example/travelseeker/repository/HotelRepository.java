package com.example.travelseeker.repository;

import com.example.travelseeker.model.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HotelRepository extends JpaRepository<Hotel, UUID> {
    Hotel findHotelById(UUID id);

    List<Hotel> findHotelsBySellerIdAndAndAvailableGreaterThan(UUID id, int available);
}
