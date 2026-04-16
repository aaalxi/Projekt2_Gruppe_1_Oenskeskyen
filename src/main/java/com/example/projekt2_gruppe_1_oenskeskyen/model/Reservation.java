package com.example.projekt2_gruppe_1_oenskeskyen.model;

import java.time.LocalDateTime;

public class Reservation {
    private int id;
    private int wishID;
    private int reservedByUserID;
    private LocalDateTime reservedAt;

    public Reservation(int id, int wishID, int reservedByUserID, LocalDateTime reservedAt) {
        this.id = id;
        this.wishID = wishID;
        this.reservedByUserID = reservedByUserID;
        this.reservedAt = reservedAt;
    }

    public Reservation(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWishID() {
        return wishID;
    }

    public void setWishID(int wishID) {
        this.wishID = wishID;
    }

    public int getReservedByUserID() {
        return reservedByUserID;
    }

    public void setReservedByUserID(int reservedByUserID) {
        this.reservedByUserID = reservedByUserID;
    }

    public LocalDateTime getReservedAt() {
        return reservedAt;
    }

    public void setReservedAt(LocalDateTime reservedAt) {
        this.reservedAt = reservedAt;
    }
}
