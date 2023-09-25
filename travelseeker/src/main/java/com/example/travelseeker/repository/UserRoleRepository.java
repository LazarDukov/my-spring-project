package com.example.travelseeker.repository;

import com.example.travelseeker.model.entities.UserRole;
import com.example.travelseeker.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findUserRoleByRole(UserRoleEnum role);
}
