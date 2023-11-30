package com.example.travelseeker.model.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "sellers")
@DiscriminatorValue("SELLER")
public class Seller extends User {
    @OneToMany
    private List<Offers> publishedOffers;

    @OneToMany
    private List<Offers> sealedOffers;

    public Seller() {
        super();
        this.publishedOffers = new ArrayList<>();
        this.sealedOffers = new ArrayList<>();
    }

    // Additional method to get the number of times an offer has been sold by the seller
   // public int getTimesOfferSold(Offers offer) {
   //     return Collections.frequency(sealedOffers, offer);
   // }

    public List<Offers> getPublishedOffers() {
        return publishedOffers;
    }

    public Seller setPublishedOffers(List<Offers> publishedOffers) {
        this.publishedOffers = publishedOffers;
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
