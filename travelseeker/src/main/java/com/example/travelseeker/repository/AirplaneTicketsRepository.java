package com.example.travelseeker.repository;

import com.example.travelseeker.model.entities.AirplaneTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneTicketsRepository extends JpaRepository<AirplaneTicket, Long> {

}
