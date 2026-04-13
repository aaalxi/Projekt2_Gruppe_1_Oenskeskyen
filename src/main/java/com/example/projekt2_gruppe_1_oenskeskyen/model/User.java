package com.example.projekt2_gruppe_1_oenskeskyen.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
    private int id;
    private String username;
    private String email;
    private LocalDate birthday;
    private LocalDateTime createdAt;
    private String password;

    public User(String username, String email, String password, LocalDate birthday) {
        this.username = username;
        this.email = email;
        this.birthday = birthday;
        this.password = password;
    }

    public User(){
    }

    public User(int id, String username, String email, LocalDate birthday, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.birthday = birthday;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
