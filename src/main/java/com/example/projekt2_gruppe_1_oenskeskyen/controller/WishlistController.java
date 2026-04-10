package com.example.projekt2_gruppe_1_oenskeskyen.controller;

import com.example.projekt2_gruppe_1_oenskeskyen.model.Wishlist;
import com.example.projekt2_gruppe_1_oenskeskyen.service.WishlistService;
import com.example.projekt2_gruppe_1_oenskeskyen.repository.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class WishlistController {

    @Autowired
    WishlistRepo wishlistRepo;

    @Autowired
    WishlistService wishlistService;

    @GetMapping("/wishlists")
    public String wishlistPage(Model model){
        int userID = 1; // midlertidigt
        ArrayList<Wishlist> wishlists = wishlistRepo.getWishlistsbyUserID(userID);
        model.addAttribute("wishlists", wishlists);
        System.out.println(wishlists.size());

        return "wishlists";
    }

    @GetMapping("/wishlists/create")
    public String showCreateWishlist(){
        return "create-wishlist";
    }

    @PostMapping("/wishlists/create")
    public String createWishlist(@RequestParam("title") String title){
        Wishlist wishlist = new Wishlist(1,title,null); //Hardcodet hvilken user. Tror vi skal bruge noget session til at vide hvem er logget ind.
        wishlistService.createWishlist(wishlist);
        return "redirect:/wishlists";
    }

    @PostMapping("/wishlists/delete")
    public String deleteWishlist(@RequestParam("ID") int id) {
        wishlistService.deleteWishlist(id);

        return "redirect:/wishlists";
    }
}
