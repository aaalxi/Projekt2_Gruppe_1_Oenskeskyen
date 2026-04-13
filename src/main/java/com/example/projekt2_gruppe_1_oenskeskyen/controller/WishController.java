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

@Controller
public class WishController {

    @Autowired
    WishService wishService;

    @Autowired
    WishlistService wishlistService;

    @GetMapping("/wishlist/add")
    public String getAddWishHTML(@RequestParam("id") int id, Model model){

        Wishlist wishlist = wishlistService.findWishlistByID(id);
        model.addAttribute("wishlist", wishlist);
        return "add-wish";
    }

    @PostMapping("/wishlist/add")
    public String addWishToWishlist(@RequestParam("name") String name,
                                   @RequestParam("url") String url,
                                   @RequestParam("description") String description,
                                   @RequestParam("price") double price,
                                   @RequestParam("priority") int priority,
                                    @RequestParam("id") int wishlistId) {
        Wish wish = new Wish(name, url, description, price, priority, wishlistId);
        wishService.createWish(wish);
        return  "redirect:/profile/wishlistId=" + wishlistId;
    }

    @PostMapping("/wishlist/delete")
    public String deleteWishFromWishlist(@RequestParam("wishId") int wishId,
                                         @RequestParam("wishlistId") int wishlistId,
                                         HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }
        wishService.deleteWishByWishId(wishId, user.getId(), wishlistId);
        return "redirect:/profile/wishlistId=" + wishlistId;
    }
}
