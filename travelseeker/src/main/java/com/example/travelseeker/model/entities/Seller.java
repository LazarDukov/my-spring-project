package com.example.travelseeker.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "sellers")
@DiscriminatorValue("SELLER")
public class Seller extends User {


    @OneToMany(cascade = CascadeType.ALL)
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

    // Additional method to get the number of times an offer has been sold by the seller
   // public int getTimesOfferSold(Offers offer) {
   //     return Collections.frequency(sealedOffers, offer);
   // }





    public List<Offers> getSealedOffers() {
        return sealedOffers;
    }

    public Seller setSealedOffers(List<Offers> sealedOffers) {
        this.sealedOffers = sealedOffers;
        return this;
    }
}
