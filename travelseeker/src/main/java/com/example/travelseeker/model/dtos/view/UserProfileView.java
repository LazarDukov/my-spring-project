package com.example.travelseeker.model.dtos.view;

public class UserProfileView {
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    private String country;
    private int age;
    private String role;

    public String getCountry() {
        return country;
    }

    public UserProfileView setCountry(String country) {
        this.country = country;
        return this;
    }

    public UserProfileView(String username, String firstName, String lastName, String email, int age, String role, String country) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.role = role;
        this.country = country;
    }

    public String getUsername() {
        return username;
    }

    public UserProfileView setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserProfileView setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserProfileView setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileView setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserProfileView setAge(int age) {
        this.age = age;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserProfileView setRole(String role) {
        this.role = role;
        return this;
    }
}
