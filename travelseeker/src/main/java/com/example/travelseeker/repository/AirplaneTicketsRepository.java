package com.example.travelseeker.repository;

import com.example.travelseeker.model.entities.AirplaneTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AirplaneTicketsRepository extends JpaRepository<AirplaneTicket, UUID> {
    AirplaneTicket findAirplaneTicketById(UUID id);

    List<AirplaneTicket> findAirplaneTicketsBySellerIdAndAndAvailableGreaterThan(UUID id, int available);

}
