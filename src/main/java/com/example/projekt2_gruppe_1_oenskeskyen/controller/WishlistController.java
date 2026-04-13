package com.example.projekt2_gruppe_1_oenskeskyen.controller;

import com.example.projekt2_gruppe_1_oenskeskyen.model.User;
import com.example.projekt2_gruppe_1_oenskeskyen.model.Wish;
import com.example.projekt2_gruppe_1_oenskeskyen.model.Wishlist;
import com.example.projekt2_gruppe_1_oenskeskyen.service.WishService;
import com.example.projekt2_gruppe_1_oenskeskyen.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WishlistController {

    @Autowired
    WishlistService wishlistService;

    @Autowired
    WishService wishService;

    @GetMapping("/wishlists")
    public String wishlistPage(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Wishlist> wishlists = wishlistService.getAllWishlistsByUserID(user.getId());
        model.addAttribute("wishlists", wishlists);
        return "wishlists";
    }

    @GetMapping("/wishlists/create")
    public String showCreateWishlist(){
        return "create-wishlist";
    }

    @PostMapping("/wishlists/create")
    public String createWishlist(@RequestParam("title") String title, HttpSession session){
        User user = (User) session.getAttribute("user");
        Wishlist wishlist = new Wishlist(user.getId(),title,null);
        wishlistService.createWishlist(wishlist);
        return "redirect:/wishlists";
    }

    @PostMapping("/wishlists/delete")
    public String deleteWishlist(@RequestParam("ID") int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        wishlistService.deleteWishlist(id, user.getId());

        return "redirect:/wishlists";
    }

    @GetMapping("/wishlists/{id}")
    public String showWishlist(@PathVariable int id, Model model){
        Wishlist wishlist = wishlistService.findWishlistByID(id);
        ArrayList<Wish> wishes = wishService.getWishesByWishlistID(wishlist.getID());
        model.addAttribute("wishlist", wishlist);
        model.addAttribute("wishes", wishes);
        return "wishlist";
    }
}
