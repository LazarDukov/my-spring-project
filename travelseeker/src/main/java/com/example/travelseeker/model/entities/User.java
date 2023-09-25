package com.example.travelseeker.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column
    private String username;

    @Column
    private String firstName;
    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String country;

    @Column
    private int age;

    @Column
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRole> roles;

    @OneToMany
    private List<Cart> cart;

    @OneToMany
    private List<BoughtOffers> boughtOffers;

    public User() {
    }

   public User(String username, String firstName, String lastName, String email,
                String country, int age, String password) {

        setUsername(username);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setCountry(country);
        setAge(age);
        setPassword(password);
        this.cart = new ArrayList<>();
        this.roles = new ArrayList<>();
        this.boughtOffers = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public List<BoughtOffers> getBoughtOffers() {
        return boughtOffers;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setCountry(String country) {
        this.country = country;
        return this;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setRoles(List<UserRole> roles) {
        this.roles = roles;
        return this;
    }

    public User setCart(List<Cart> cart) {
        this.cart = cart;
        return this;
    }

    public User setBoughtOffers(List<BoughtOffers> boughtOffers) {
        this.boughtOffers = boughtOffers;
        return this;
    }
}
