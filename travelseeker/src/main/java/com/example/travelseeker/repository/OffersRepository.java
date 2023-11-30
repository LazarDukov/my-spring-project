package com.example.travelseeker.repository;

import com.example.travelseeker.model.entities.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OffersRepository extends JpaRepository<Offers, UUID> {


    @Modifying
    @Query(value = "ALTER TABLE sellers_sealed_offers ADD COLUMN counter VARCHAR(255)", nativeQuery = true)
    void addNewColumn();

    @Query("SELECT o FROM Offers o JOIN o.airplaneTickets t WHERE t.id = :id")
    Offers findByAirplaneTicketId(@Param("id") UUID id);

    @Query("SELECT o FROM Offers o JOIN o.hotels h WHERE h.id = :id")
    Offers findByHotelId(@Param("id") UUID id);

    @Query("SELECT o FROM Offers o JOIN o.carRents c WHERE c.id = :id")
    Offers findByCarRentId(@Param("id") UUID id);
}
