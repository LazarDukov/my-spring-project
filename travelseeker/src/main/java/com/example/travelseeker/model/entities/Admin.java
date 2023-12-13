package com.example.travelseeker.model.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    public Admin() {
        super();
    }


}
