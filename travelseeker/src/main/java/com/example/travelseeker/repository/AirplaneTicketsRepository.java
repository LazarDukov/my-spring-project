package com.example.travelseeker.repository;

import com.example.travelseeker.model.entities.AirplaneTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneTicketsRepository extends JpaRepository<AirplaneTicket, Long> {
    AirplaneTicket findAirplaneTicketById(Long id);

    AirplaneTicket findAirplaneTicketBySellerId(Long id);
}
