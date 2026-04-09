package com.example.projekt2_gruppe_1_oenskeskyen.controller;

import com.example.projekt2_gruppe_1_oenskeskyen.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WishlistController {

    @Autowired
    WishlistService wishlistService;

  @GetMapping("/wishlists/create")
    public String showCreateWishlist(HttpSession session){

      return "create-wishlist";
  }

  @PostMapping("/wishlists/create")
    public String createWishlist(@RequestParam String title, HttpSession session){

      return "redirect:/wishlists";
  }
}
