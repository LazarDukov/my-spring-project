package com.example.travelseeker.repository;

import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.model.entities.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OffersRepository extends JpaRepository<Offers, UUID> {
    @Query("SELECT DISTINCT airplaneTicket FROM Offers o " +
            "JOIN o.buyers b " +
            "JOIN o.airplaneTickets airplaneTicket " +
            "WHERE b.id = :buyerId")
    List<AirplaneTicket> findAirplaneTicketsByBuyerId(@Param("buyerId") UUID buyerId);
}
