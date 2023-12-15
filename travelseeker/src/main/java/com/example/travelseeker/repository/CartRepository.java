package com.example.travelseeker.repository;

import com.example.travelseeker.model.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
    List<Cart> findCartsByBuyer_Id(UUID id);
}
