package com.example.travelseeker.model.dtos;

import com.example.travelseeker.model.entities.Cart;
import com.example.travelseeker.model.entities.UserRole;
import com.example.travelseeker.model.enums.UserRoleEnum;
//import com.example.travelseeker.util.validation.UsernameValidatorInterface;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class UserRegistrationDTO {

    @Size(min = 4, max = 30)
    @NotBlank
    //@UsernameValidatorInterface(message = "User with this username already exists!")
    private String username;
    @NotBlank
    @Size(min = 4, max = 30)
    private String password;
    @NotNull
    @Size(min = 4, max = 30)
    private String confirmPassword;
    @NotBlank
    @Size(min = 4, max = 30)
    private String firstName;
    @NotBlank
    @Size(min = 4, max = 30)
    private String lastName;
    @Email
    @NotBlank
    private String email;

    private int age;
    @NotBlank
    @Size(min = 4, max = 30)
    private String country;

    @Enumerated(EnumType.STRING)
    private String role;




    public UserRegistrationDTO() {
        this.firstName = "Anonymous";
        this.lastName = "Anonymous";
        this.age = 0;
        this.country = "Missing";
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public UserRegistrationDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserRegistrationDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserRegistrationDTO setAge(int age) {
        this.age = age;
        return this;
    }

    public UserRegistrationDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public String getRole() {
        return role;
    }

    public UserRegistrationDTO setRole(String role) {
        this.role = role;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegistrationDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }


    public String getEmail() {
        return email;
    }

    public UserRegistrationDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
