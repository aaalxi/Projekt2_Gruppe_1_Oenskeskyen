package com.example.projekt2_gruppe_1_oenskeskyen.service;

import com.example.projekt2_gruppe_1_oenskeskyen.model.Wish;
import com.example.projekt2_gruppe_1_oenskeskyen.model.Wishlist;
import com.example.projekt2_gruppe_1_oenskeskyen.repository.WishRepo;
import com.example.projekt2_gruppe_1_oenskeskyen.repository.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WishService {

    @Autowired
    private WishRepo wishRepo;

    @Autowired
    private WishlistRepo wishlistRepo;

    public Wish findWishByWishId(int id) {
        return wishRepo.findWishByWishID(id);
    }

    public void deleteWishByWishId(int wishId, int userId) {
        Wish wish = wishRepo.findWishByWishID(wishId);
        if (wish == null) {
            System.out.println("no wish found");
            return;
        }

        Wishlist wishlist = wishlistRepo.findWishlistByWishlistId(wish.getWishListID());
        if (wishlist == null) {
            System.out.println("No wishlist found");
            return;
        }

        if (wishlist.getUserID() != userId) {
            throw new SecurityException("User not authorized");
        }

        wishRepo.deleteWishByWishId(wishId);
    }

    public void createWish(Wish wish){
        wishRepo.createWish(wish);
    }

    public ArrayList<Wish> getWishesByWishlistID(int id) {
        return wishRepo.getWishesByWishlistID(id);
    }

    public void updateWish(Wish wish) {
        wishRepo.updateWishById(wish);
    }
}