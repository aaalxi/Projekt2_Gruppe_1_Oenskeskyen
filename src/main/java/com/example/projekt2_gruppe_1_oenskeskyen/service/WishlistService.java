package com.example.projekt2_gruppe_1_oenskeskyen.service;

import com.example.projekt2_gruppe_1_oenskeskyen.model.Wishlist;
import com.example.projekt2_gruppe_1_oenskeskyen.repository.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepo wishlistRepo;

    public void createWishlist(Wishlist w) {
        wishlistRepo.createWishlist(w);
    }

    public List<Wishlist> getAllWishlistsByUserID(int userID) {

        return wishlistRepo.getWishlistsbyUserID(userID);
    }

    public Wishlist findWishlistByID(int ID) {
        return wishlistRepo.findWishlistByID(ID);
    }

    public void deleteWishlist(int wishlistID, int sessionUserID) {
        Wishlist wishlist = wishlistRepo.findWishlistByID(wishlistID);
        if(wishlist != null && wishlist.getUserID() == sessionUserID){
            wishlistRepo.deleteWishlistByID(wishlistID);
        }
    }
}
