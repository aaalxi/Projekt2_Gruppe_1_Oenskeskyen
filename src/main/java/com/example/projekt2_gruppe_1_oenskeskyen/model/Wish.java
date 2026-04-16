package com.example.projekt2_gruppe_1_oenskeskyen.model;

import java.time.LocalDateTime;

public class Wish {
    private int ID;
    private int wishlistID;
    private String name;
    private String url;
    private String description;
    private double price;
    private String currency;
    private int priority;
    private LocalDateTime createdAt;
    //SQL script sætter selv createdAt + id,  LocalDateTime til now(), autogen. id, wishListID foreing key ikke
    //med i konstruktør


    public Wish(String name, String url, String description, double price, String currency, int priority, int wishlistID) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.price = price;
        this.priority=priority;
        this.wishlistID = wishlistID;
        this.currency = currency;
    }

    public Wish(int ID, int wishlistID, String name, String description, String url, double price, String currency, int priority, LocalDateTime createdAt) {
        this.ID = ID;
        this.wishlistID = wishlistID;
        this.name = name;
        this.description = description;
        this.url = url;
        this.price = price;
        this.currency = currency;
        this.priority = priority;
        this.createdAt = createdAt;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}