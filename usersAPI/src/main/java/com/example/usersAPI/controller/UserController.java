package com.example.usersAPI.controller;

import com.example.usersAPI.model.User;
import com.example.usersAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public String newUser (@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping
    public String updateUser (@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser (@PathVariable int id){
        return userService.deleteUserByID(id);
    }

    @GetMapping("/{id}")
    public User getUserByID (@PathVariable int id){
        return userService.getUserByID(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers (){
        return userService.getAllUsers();
    }
}
