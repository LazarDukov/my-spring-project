package com.example.travelseeker.repository;

import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SellerRepository extends JpaRepository<Seller, UUID> {
    Optional<Seller> findSellerByUsername(String username);
    Optional<Seller> findSellerByEmail(String email);
}
