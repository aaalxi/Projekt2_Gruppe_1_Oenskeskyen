package com.example.projekt2_gruppe_1_oenskeskyen.controller;

import com.example.projekt2_gruppe_1_oenskeskyen.model.Wishlist;
import com.example.projekt2_gruppe_1_oenskeskyen.service.WishService;
import com.example.projekt2_gruppe_1_oenskeskyen.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WishController {

    @Autowired
    WishService wishService;


}
