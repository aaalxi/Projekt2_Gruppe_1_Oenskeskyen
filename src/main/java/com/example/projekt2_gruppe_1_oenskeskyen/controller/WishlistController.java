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
import java.util.UUID;


@Controller
public class WishlistController {

    @Autowired
    WishlistService wishlistService;

    @Autowired
    WishService wishService;

    @GetMapping("/profile/createWishlist")
    public String showCreateWishlist() {
        return "create-wishlist";
    }

    @PostMapping("/profile/createWishlist")
    public String createWishlist(@RequestParam("title") String title, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Wishlist wishlist = new Wishlist(user.getId(), title, null);
        wishlistService.createWishlist(wishlist);
        return "redirect:/profile";
    }

    @PostMapping("/profile/deleteWishlist")
    public String deleteWishlist(@RequestParam("ID") int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        wishlistService.deleteWishlist(id, user.getId());

        return "redirect:/profile";
    }

    @GetMapping("/profile/wishlist/{id}")
    public String showWishlist(@PathVariable int id, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        Wishlist wishlist = wishlistService.findWishlistByID(id);
        if (wishlist == null || wishlist.getUserID() != user.getId()) {
            return "redirect:/profile";
        }

        ArrayList<Wish> wishes = wishService.getWishesByWishlistID(wishlist.getID());
        model.addAttribute("wishlist", wishlist);
        model.addAttribute("wishes", wishes);
        return "wishlist";
    }

    @GetMapping("/wishlist/{id}/edit")
    public String showUpdateWishlist(@PathVariable int id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        Wishlist wishlist = wishlistService.findWishlistByID(id);
        model.addAttribute("wishlist", wishlist);

        return "edit-wishlist";
    }

    @PostMapping("/wishlist/{id}/edit")
    public String updateWishlist(@PathVariable int id, @RequestParam(required = false) String sharetoken, @RequestParam String title, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) return "redirect:/login";
        wishlistService.updateWishlist(id, title, user.getId(), sharetoken);

        return "redirect:/profile/wishlist/" + id;
    }

    @PostMapping("/profile/wishlist/share/{id}")
    public String setWishlistShareTokenAtShare(@PathVariable int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Wishlist wishlist = wishlistService.findWishlistByID(id);
        if (wishlist == null || wishlist.getUserID() != user.getId()) {
            return "redirect:/profile";
        }

        if (wishlist.getShareToken() == null) {
            wishlistService.setShareToken(id, UUID.randomUUID().toString());
        }
        return "redirect:/profile/wishlist/" + id;
    }

    @GetMapping("/wishlist/share/{token}")
    public String showSharedWishlist(@PathVariable String token, Model model) {
        Wishlist wishlist = wishlistService.findWishlistByShareToken(token);
        if (wishlist == null) {
            return "redirect:/login";
        }
        ArrayList<Wish> wishes = wishService.getWishesByWishlistID(wishlist.getID());
        model.addAttribute("wishlist", wishlist);
        model.addAttribute("wishes", wishes);

        return "shared-wishlist";
    }
}
