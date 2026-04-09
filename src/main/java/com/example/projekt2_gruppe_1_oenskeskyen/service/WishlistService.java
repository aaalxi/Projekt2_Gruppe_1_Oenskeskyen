package com.example.projekt2_gruppe_1_oenskeskyen.service;

import com.example.projekt2_gruppe_1_oenskeskyen.model.Wishlist;
import com.example.projekt2_gruppe_1_oenskeskyen.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    public void createWishlist(Wishlist w) {
        wishlistRepository.createWishlist(w);

    }

//    public List<Wishlist> getAllWishlistsByUserID(int userID) {
//
//        return wishlistRepo.getAllWishlistsByUserID(userID);
//    }

    public Wishlist findWishlistByID(int ID) {
        return wishlistRepository.findWishlistByID(ID);

    }
}
