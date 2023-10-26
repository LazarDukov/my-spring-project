package com.example.travelseeker.repository;

import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AirplaneTicketsRepository extends JpaRepository<AirplaneTicket, UUID> {
    AirplaneTicket findAirplaneTicketById(UUID id);

    @Query("SELECT DISTINCT user FROM AirplaneTicket ticket JOIN ticket.sellers user WHERE ticket.id = :ticketId")
    List<User> findUsersByTicketId(@Param("ticketId") UUID ticketId);
}
