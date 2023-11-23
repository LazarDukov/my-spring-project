package com.example.travelseeker.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "buyers")
@DiscriminatorValue("BUYER")
public class Buyer extends User {

    @OneToOne
    private Cart cart;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Offers> boughtOffers;

    public Buyer() {
        super();
        setCart(new Cart());

        this.boughtOffers = new ArrayList<>();
    }


    public Cart getCart() {
        return cart;
    }

    public Buyer setCart(Cart cart) {
        this.cart = cart;
        return this;
    }

    public List<Offers> getBoughtOffers() {
        return boughtOffers;
    }

    public Buyer setBoughtOffers(List<Offers> boughtOffers) {
        this.boughtOffers = boughtOffers;
        return this;
    }
}
