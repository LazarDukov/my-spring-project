package com.example.travelseeker.repository;

import com.example.travelseeker.model.entities.Admin;
import com.example.travelseeker.model.entities.Buyer;
import com.example.travelseeker.service.ApplicationUserDetailsService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {
    Optional<Admin> findAdminByUsername(String username);
}
