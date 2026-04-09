package com.example.projekt2_gruppe_1_oenskeskyen.model;

import java.time.LocalDateTime;

public class Wishlist {
    private int ID;
    private int userID;
    private String title;
    private String shareToken;
    private LocalDateTime createdAt;

    public Wishlist(int userID, String title, String shareToken) {
        this.userID = userID;
        this.title = title;
        this.shareToken = shareToken;
    }
    public Wishlist(){
    }

    public int getID() {
        return ID;
    }

    public int getUserID() {
        return userID;
    }

    public String getTitle() {
        return title;
    }

    public String getShareToken() {
        return shareToken;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShareToken(String shareToken) {
        this.shareToken = shareToken;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
