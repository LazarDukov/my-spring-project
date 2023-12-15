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
public class AddAdminDTO {

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


    private String firstName;
    private String lastName;


    private int age;

    private String country;


    public AddAdminDTO() {
        this.firstName = "Admin";
        this.lastName = "Admin";
        this.age = 0;
        this.country = "Missing";
    }

    public String getUsername() {
        return username;
    }

    public AddAdminDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AddAdminDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public AddAdminDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AddAdminDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public AddAdminDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AddAdminDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getAge() {
        return age;
    }

    public AddAdminDTO setAge(int age) {
        this.age = age;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public AddAdminDTO setCountry(String country) {
        this.country = country;
        return this;
    }
}