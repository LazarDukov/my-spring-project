package com.example.travelseeker.model.dtos;

import com.example.travelseeker.model.entities.Cart;
import com.example.travelseeker.model.enums.UserRoleEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class EditProfileDTO {

    @Size(min = 4, max = 30)
    @NotNull
    private String username;

    @NotNull
    @Size(min = 4, max = 30)
    private String firstName;
    @NotNull
    @Size(min = 4, max = 30)
    private String lastName;
    @Email
    @NotNull
    private String email;
    private int age;
    @NotNull
    private String country;

    @Enumerated(EnumType.STRING)
    @NotNull
    private String role;

    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public EditProfileDTO setCart(Cart cart) {
        this.cart = cart;
        return this;
    }

    public EditProfileDTO() {

    }

    public UserRoleEnum getRole() {
        return UserRoleEnum.valueOf(role);
    }

    public EditProfileDTO setRole(String role) {
        this.role = role;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public EditProfileDTO setUsername(String username) {
        this.username = username;
        return this;
    }


    public String getFirstName() {
        return firstName;
    }

    public EditProfileDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EditProfileDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getAge() {
        return age;
    }

    public EditProfileDTO setAge(int age) {
        this.age = age;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public EditProfileDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EditProfileDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
