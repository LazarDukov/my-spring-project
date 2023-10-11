package com.example.travelseeker.repository;

import com.example.travelseeker.model.entities.AirplaneTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirplaneTicketsRepository extends JpaRepository<AirplaneTicket, Long> {

}
