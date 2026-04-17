package com.example.projekt2_gruppe_1_oenskeskyen.service;

import com.example.projekt2_gruppe_1_oenskeskyen.model.Reservation;
import com.example.projekt2_gruppe_1_oenskeskyen.repository.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    @Autowired
    ReservationRepo reservationRepo;

    public void createReservation(int wishID, int reservingUserID){
        Reservation existingReservation = reservationRepo.getReservationByWishID(wishID);
        if(existingReservation != null){
            return;
        }
        reservationRepo.createReservationForWish(wishID, reservingUserID);
    }

    public void deleteReservation(int wishID){
        reservationRepo.deleteReservationForWish(wishID);
    }

    public Reservation getReservationByWishID(int wishID){
        Reservation reservation = reservationRepo.getReservationByWishID(wishID);
        return reservation;
    }
}
