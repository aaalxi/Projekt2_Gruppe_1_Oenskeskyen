package com.example.projekt2_gruppe_1_oenskeskyen.controller;
import com.example.projekt2_gruppe_1_oenskeskyen.model.User;
import com.example.projekt2_gruppe_1_oenskeskyen.model.Wishlist;
import com.example.projekt2_gruppe_1_oenskeskyen.service.UserService;
import com.example.projekt2_gruppe_1_oenskeskyen.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    WishlistService wishlistService;

    @GetMapping("/user-register")
    public String showRegisterUser(){
        return "register-user";
    }

    @PostMapping("/user-register")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("birthday")LocalDate birthday,
                               HttpSession session){

        User user = new User(username,email,password,birthday);
        userService.register(user);

        User savedUser = userService.login(username,password);
        session.setAttribute("user", savedUser);
        return "redirect:/profile";
    }

    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session){
        User user = userService.login(username, password);
        if(user != null){
            session.setAttribute("user", user);
            return "redirect:/profile";
        }
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }
        List<Wishlist> wishlists = wishlistService.getAllWishlistsByUserID(user.getId());
        model.addAttribute("wishlists", wishlists);

        return "profile";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/profile/edit-profile/delete")
    public String deleteProfile(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }

        userService.deleteUserByUserId(user);
        return "redirect:/user-register";
    }

    @PostMapping("/profile/edit-profile")
    public String editProfile(@RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("birthday") LocalDate birthday,
            @RequestParam("password") String password,
            @RequestParam("userId") int userId,
            HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }
        if (user.getId() != userId){
            return "redirect:/profile";
        }

        userService.updateUserByUserId(userId, username, email, birthday, password);

        return "redirect:/edit-profile";
    }

    /*
    HttpSession er en mekanisme i spring der bruges til at
    huske data mellem HTTP requests.

    serveren husker ikke hvem man er mellem requests, så hver gang man
    besøger en ny side er det som om serveren ser en for første gang.

    HttpSession fikser det ved at gemme data på serveren og knytte det til
    en spesifik user ved et session ID der auto sendes frem og tilbage i en cookie.
     */
}
