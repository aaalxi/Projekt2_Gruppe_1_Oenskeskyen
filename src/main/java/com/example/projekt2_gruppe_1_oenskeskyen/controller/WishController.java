package com.example.projekt2_gruppe_1_oenskeskyen.controller;

import com.example.projekt2_gruppe_1_oenskeskyen.model.User;
import com.example.projekt2_gruppe_1_oenskeskyen.model.Wish;
import com.example.projekt2_gruppe_1_oenskeskyen.model.Wishlist;
import com.example.projekt2_gruppe_1_oenskeskyen.repository.WishRepo;
import com.example.projekt2_gruppe_1_oenskeskyen.service.WishService;
import com.example.projekt2_gruppe_1_oenskeskyen.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WishController {

    @Autowired
    WishService wishService;

    @Autowired
    WishlistService wishlistService;
    @Autowired
    private WishRepo wishRepo;

    @GetMapping("/wishlist/add")
    public String getAddWishHTML(@RequestParam("id") int id, Model model) {

        Wishlist wishlist = wishlistService.findWishlistByID(id);
        model.addAttribute("wishlist", wishlist);
        return "add-wish";
    }

    @PostMapping("/wishlist/add")
    public String addWishToWishlist(@RequestParam("name") String name,
                                    @RequestParam("url") String url,
                                    @RequestParam("description") String description,
                                    @RequestParam("price") double price,
                                    @RequestParam("currency") String currency,
                                    @RequestParam("priority") int priority,
                                    @RequestParam("id") int wishlistId) {
        Wish wish = new Wish(name, url, description, price, currency, priority, wishlistId);
        wishService.createWish(wish);
        return "redirect:/profile/wishlist/" + wishlistId;
    }

    @PostMapping("/wishlist/delete")
    public String deleteWishFromWishlist(@RequestParam("wishId") int wishId,
                                         @RequestParam("wishlistId") int wishlistId,
                                         HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        wishService.deleteWishByWishId(wishId, user.getId());

        return "redirect:/profile/wishlist/" + wishlistId;
    }

    @GetMapping("/wish/update")
    public String editWish(@RequestParam("ID") int ID, Model model) {
        Wish wish = wishService.findWishByWishId(ID);
        model.addAttribute("wish", wish);
        return "update-wish";
    }

    @PostMapping("/wish/update")
    public String editWish(@RequestParam("id") int ID,
                           @RequestParam("wishlistID") int wishlistID,
                           @RequestParam("name") String name,
                           @RequestParam("url") String url,
                           @RequestParam("description") String description,
                           @RequestParam("price") double price,
                           @RequestParam("currency") String currency,
                           @RequestParam("priority") int priority) {
        Wish wish = new Wish(name, url, description, price, currency, priority, wishlistID);
        wishService.updateWish(wish);
        return "redirect:/profile/wishlist/" + ID;
    }
}
