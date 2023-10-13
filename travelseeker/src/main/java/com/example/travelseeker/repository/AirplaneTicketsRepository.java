package com.example.travelseeker.repository;

import com.example.travelseeker.model.entities.AirplaneTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirplaneTicketsRepository extends JpaRepository<AirplaneTicket, Long> {
    Optional<AirplaneTicket> findAirplaneTicketById(Long id);
}
