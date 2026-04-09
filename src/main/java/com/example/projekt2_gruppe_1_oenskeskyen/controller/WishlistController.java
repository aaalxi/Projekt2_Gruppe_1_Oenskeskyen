package com.example.projekt2_gruppe_1_oenskeskyen.controller;

import com.example.projekt2_gruppe_1_oenskeskyen.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class WishlistController {

    @Autowired
    WishlistRepository wishlistRepository;

    @GetMapping("/wishlists")
    public String wishlistPage(Model model){
        int userID = 1; // midlertidigt
        ArrayList<Wishlist> wishlists = wishlistRepository.getWishlistsbyUserID(userID);

        return "wishlists";
    }
}
