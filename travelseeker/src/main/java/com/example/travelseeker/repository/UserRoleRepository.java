package com.example.travelseeker.repository;

import com.example.travelseeker.model.entities.UserRole;
import com.example.travelseeker.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {

    UserRole findUserRoleByRole(UserRoleEnum role);

}
