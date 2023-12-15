package com.example.travelseeker.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sellers")
@DiscriminatorValue("SELLER")
public class Seller extends User {


    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "seller_sealed_offers",
            joinColumns = @JoinColumn(name = "seller_id"),
            inverseJoinColumns = @JoinColumn(name = "sealed_offer_id")
    )
    private List<Offers> sealedOffers;

    public Seller() {
        super();

        this.sealedOffers = new ArrayList<>();
    }

    public Seller addRole(UserRole role) {
        this.getRoles().add(role);
        return this;
    }

    public List<Offers> getSealedOffers() {
        return sealedOffers;
    }

    public Seller setSealedOffers(List<Offers> sealedOffers) {
        this.sealedOffers = sealedOffers;
        return this;
    }
}
