package com.example.projekt2_gruppe_1_oenskeskyen.service;

import com.example.projekt2_gruppe_1_oenskeskyen.model.Wish;
import com.example.projekt2_gruppe_1_oenskeskyen.model.Wishlist;
import com.example.projekt2_gruppe_1_oenskeskyen.repository.WishRepo;
import com.example.projekt2_gruppe_1_oenskeskyen.repository.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishService {

    @Autowired
    private WishRepo wishRepo;

    @Autowired
    private WishlistRepo wishlistRepo;

    public Wish findById(int id) {
        return wishRepo.findById(id);
    }

    public void deleteWish(int wishId, int userId) {
        Wish wish = wishRepo.findById(wishId);

        Wishlist wishlist = wishlistRepo.findById(wish.getWishListId());

        if (wishlist.getUserId() != userId) {
            throw new SecurityException("User not found");
        }

        wishRepo.deleteById(wishId);
    }

}