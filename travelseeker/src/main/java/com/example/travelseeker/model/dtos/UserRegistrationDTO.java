package com.example.travelseeker.model.dtos;

import com.example.travelseeker.util.validation.EmailValidatorInterface;
import com.example.travelseeker.util.validation.PasswordMatchValidatorInterface;
import com.example.travelseeker.util.validation.UsernameValidatorInterface;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@PasswordMatchValidatorInterface(password = "password", confirmPassword = "confirmPassword")
public class UserRegistrationDTO {

    @Size(min = 4, max = 30, message = "Username should be between 4 and 30 symbols!")
    @NotBlank(message = "Username cannot be empty!")
    @UsernameValidatorInterface(message = "User with this username already exists!")
    private String username;


    @Size(min = 4, max = 30, message = "Password should be between 4 and 30 symbols!")
    @NotBlank(message = "Password cannot be empty!")
    private String password;

    private String confirmPassword;

    @Email
    @NotBlank(message = "Email cannot be empty!")
    @EmailValidatorInterface(message = "User with this email already exists!")
    private String email;

    @NotBlank(message = "You should select option!")
    @Enumerated(EnumType.STRING)
    private String role;


    private String firstName;
    private String lastName;


    private int age;

    private String country;


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
