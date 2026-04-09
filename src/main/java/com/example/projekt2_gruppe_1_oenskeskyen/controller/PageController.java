package com.example.projekt2_gruppe_1_oenskeskyen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String mainPage() {

        return "index";
    }

    @GetMapping("/wishlists")
    public String wishlistPage(){
        return "wishlists";
    }
}