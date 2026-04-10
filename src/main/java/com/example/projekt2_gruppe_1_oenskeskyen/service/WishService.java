package com.example.projekt2_gruppe_1_oenskeskyen.service;

import com.example.projekt2_gruppe_1_oenskeskyen.model.Wish;
import com.example.projekt2_gruppe_1_oenskeskyen.repository.WishRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class WishService {

    @Autowired
    WishRepo wishRepo;

    public void createWish(Wish wish){
        wishRepo.createWish(wish);
    }

    public ArrayList<Wish> getWishesByWishlistID(int id) {
        return wishRepo.getWishesByWishlistID(id);
    }

}