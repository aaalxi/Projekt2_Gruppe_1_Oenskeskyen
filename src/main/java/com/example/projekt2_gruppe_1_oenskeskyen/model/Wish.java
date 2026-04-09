package com.example.projekt2_gruppe_1_oenskeskyen.model;

import java.time.LocalDateTime;

public class Wish {
    private int id;
    private int wishListId;
    private String name;
    private String description;
    private double price;
    private int priority;
    private LocalDateTime createdAt;


    public Wish(){
    }

    public Wish(int id, int wishListId, String name, String description, double price, int priority, LocalDateTime createdAt) {
        this.id = id;
        this.wishListId = wishListId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.priority = priority;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWishListId() {
        return wishListId;
    }

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
