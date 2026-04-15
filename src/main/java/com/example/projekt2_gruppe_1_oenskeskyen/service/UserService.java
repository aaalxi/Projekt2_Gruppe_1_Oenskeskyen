package com.example.projekt2_gruppe_1_oenskeskyen.service;
import com.example.projekt2_gruppe_1_oenskeskyen.model.User;
import com.example.projekt2_gruppe_1_oenskeskyen.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public void register(User user) {
        userRepo.createUser(user);
    }

    public User login(String username, String password) {
        User user = userRepo.findByUsername(username);
        if(user != null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

    public void deleteUserByUserId(User user) {
        userRepo.deleteUserByUserId(user);
    }

    public void updateUserByUserId(int id, String username, String email, LocalDate birthday, String password){
        User user = userRepo.getUserbyUserID(id);

        user.setUsername(username);
        user.setEmail(email);
        user.setBirthday(birthday);
        user.setPassword(password);

        userRepo.updateUserByUserId(user);
    }

    public User getUserByUserId(int id){
        return userRepo.getUserbyUserID(id);
    }
}
