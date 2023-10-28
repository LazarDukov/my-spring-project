package com.example.travelseeker.model.entities;

import com.example.travelseeker.model.enums.UserRoleEnum;
import jakarta.persistence.*;


@Entity
@Table(name = "user_roles")
public class UserRole extends BaseEntity {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;


    public UserRole() {
    }

    public UserRole(UserRoleEnum role) {
        this.role = role;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRole setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }

}
