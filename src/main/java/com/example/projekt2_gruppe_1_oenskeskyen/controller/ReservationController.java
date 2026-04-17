package com.example.projekt2_gruppe_1_oenskeskyen.controller;

import com.example.projekt2_gruppe_1_oenskeskyen.model.Reservation;
import com.example.projekt2_gruppe_1_oenskeskyen.model.User;
import com.example.projekt2_gruppe_1_oenskeskyen.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @PostMapping("/wishlist/reserve")
    public String reserveWish(@RequestParam("wishID") int wishID, @RequestParam("token") String token, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        reservationService.createReservation(wishID, user.getId());
        return "redirect:/wishlist/share/" + token;
    }

    @PostMapping("/wishlist/cancelReservation")
    public String cancelReservation(@RequestParam("wishID") int wishID, @RequestParam("token") String token, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Reservation reservation = reservationService.getReservationByWishID(wishID);
        if (reservation == null || reservation.getReservedByUserID() != user.getId()) {
            return "redirect:/wishlist/share/" + token;
        }
        reservationService.deleteReservation(wishID);
        return "redirect:/wishlist/share/" + token;
    }
}
