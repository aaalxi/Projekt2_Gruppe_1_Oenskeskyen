package com.example.projekt2_gruppe_1_oenskeskyen.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Wish {
    private int id;
    private int wishlistID;
    private String name;
    private String url;
    private String description;
    private double price;
    private int priority;
    private LocalDateTime createdAt;
    //SQL script sætter selv createdAt + id,  LocalDateTime til now(), autogen. id, wishListID foreing key ikke
    //med i konstruktør


    public Wish(String name, String url, String description, double price, int priority) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.price = price;
        this.priority=priority;
    }

    public Wish () {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWishListID() {
        return wishlistID;
    }

    public void setWishListID(int wishListID) {
        this.wishlistID = wishListID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPriority () {
        return priority;
    }

    public void setPriority (int priority) {
        this.priority=priority;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}