package com.example.travelseeker.repository;

import com.example.travelseeker.model.entities.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, UUID> {


    Optional<Buyer> findBuyerByUsername(String username);

    Buyer findFirstByUsername(String username);

    Buyer findFirstByEmail(String email);


    Buyer findBuyerById(UUID id);
}
