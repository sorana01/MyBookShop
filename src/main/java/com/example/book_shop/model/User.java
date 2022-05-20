package com.example.book_shop.model;

import java.util.Objects;

public class User {
    private String username;
    private String password;
    private String role;
    private String full_name;
    private String address;
    private String phone_number;
    private String email;

    public User(String username, String password, String role, String full_name, String address, String phone_number, String email) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.full_name = full_name;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
    }

    public User() {
    }

    public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUsername().equals(user.getUsername()) && getPassword().equals(user.getPassword()) && getRole().equals(user.getRole()) && getFull_name().equals(user.getFull_name()) && getAddress().equals(user.getAddress()) && getPhone_number().equals(user.getPhone_number()) && getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getRole(), getFull_name(), getAddress(), getPhone_number(), getEmail());
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", full_name='" + full_name + '\'' +
                ", address='" + address + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
