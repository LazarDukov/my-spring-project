package com.example.travelseeker.model.entities;

import com.example.travelseeker.model.enums.UserRoleEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user_roles")
public class UserRole extends BaseEntity {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Buyer> buyers;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Seller> sellers;


    public UserRole() {
        this.buyers = new ArrayList<>();
        this.sellers = new ArrayList<>();
    }

    public void addSeller(Seller seller) {
        sellers.add(seller);
        seller.setRole(this);
    }

    public void addBuyer(Buyer buyer) {
        buyers.add(buyer);
        buyer.setRole(this);
    }


    public UserRoleEnum getRole() {
        return role;
    }

    public UserRole setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }

    public List<Buyer> getBuyers() {
        return buyers;
    }

    public UserRole setBuyers(List<Buyer> buyers) {
        this.buyers = buyers;
        return this;
    }

    public List<Seller> getSellers() {
        return sellers;
    }

    public UserRole setSellers(List<Seller> sellers) {
        this.sellers = sellers;
        return this;
    }
}
