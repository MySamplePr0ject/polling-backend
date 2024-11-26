package com.example.usersAPI.controller;

import com.example.usersAPI.model.User;
import com.example.usersAPI.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.RetryableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<String> newUser (@RequestBody User user){
        try{
            return new ResponseEntity<String>(userService.createUser(user), HttpStatus.CREATED);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (DataAccessException e){
            return ResponseEntity.badRequest().body("E-mail address already exists");
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @PutMapping
    public ResponseEntity<String> updateUser (@RequestBody User user){
        try {
            return new ResponseEntity<String>(userService.updateUser(user), HttpStatus.CREATED);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (DataAccessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (ClassNotFoundException e){
            return ResponseEntity.status(404).body("User doesn't exist");
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser (@PathVariable int id){
        try {
            return new ResponseEntity<String>(userService.deleteUserByID(id), HttpStatus.CREATED);
        }catch (FeignException e){
            System.out.println("Server is down. Notify on-call guy" + e);
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("We are experiencing difficulties, but don't worry, we'll have it fixed in no time");
        }catch (Exception e){
            return ResponseEntity.status(404).body("User doesn't exist");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getUserByID (@PathVariable int id){
        try {
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(userService.getUserByID(id));
//            return ResponseEntity.ok().body(userService.getUserByID(id));
        }catch (ClassNotFoundException e){
            return ResponseEntity.status(404).body(e.getMessage());    
        }catch (Exception e){
            System.out.println("Unexpected error: " + e);
            return null;
        }
    }

    @GetMapping("/users")
    public ResponseEntity<String> getAllUsers (){
        try {
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(userService.getAllUsers());
        }catch (Exception e){
            System.out.println("Unexpected error: " + e);
            return null;
        }
    }
}
