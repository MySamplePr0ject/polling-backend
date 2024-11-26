package com.example.APIGateway.controller;

import com.example.APIGateway.UsersClient;
import feign.FeignException;
import feign.RetryableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class GatewayController {
    @Autowired
    UsersClient usersClient;

    @PostMapping("/users")
    public ResponseEntity<String> registerUser (@RequestBody String jsonUser){
        try{
            ResponseEntity<String> response = usersClient.register(jsonUser);
                return ResponseEntity.status(response.getStatusCode())
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(response.getBody());
        }catch (FeignException e){
            String errorMessage = e.contentUTF8();
            return ResponseEntity.status(e.status()).body(errorMessage);
        }catch (Exception e){
            System.out.println("Gateway error: " + e.getMessage());
            return null;
        }
    }

    @PutMapping("/users")
    public ResponseEntity<String> updateUser (@RequestBody String jsonUser){
        try {
            ResponseEntity<String> response = usersClient.update(jsonUser);
            return ResponseEntity.status(response.getStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response.getBody());
        }catch (FeignException e){
            String errorMessage = e.contentUTF8();
            return ResponseEntity.status(e.status()).body(errorMessage);
        }catch (Exception e){
            System.out.println("Gateway error: " + e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser (@PathVariable int id){
        try {
            ResponseEntity<String> response = usersClient.delete(id);
            return ResponseEntity.status(response.getStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response.getBody());
        }catch (RetryableException e){
            System.out.println("Server is down. Notify on-call guy" + e);
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("We are experiencing difficulties, but don't worry, we'll have it fixed in no time");
        }catch (FeignException e){
            String errorMessage = e.contentUTF8();
            return ResponseEntity.status(e.status()).body(errorMessage);
        }catch (Exception e){
            System.out.println("Gateway error: " + e.getMessage());
            return null;
        }
    }

    @GetMapping({"/users/", "/users", "/users/{id}"})
    public ResponseEntity<String> getUsers (@PathVariable(required = false) Integer id){
        if (id != null){
            try {
                ResponseEntity<String> response = usersClient.getUserById(id);
                return ResponseEntity.status(response.getStatusCode())
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(response.getBody());
            }catch (FeignException e){
                return ResponseEntity.status(e.status()).body(e.contentUTF8());
            }catch (Exception e){
                System.out.println("Gateway error: " + e.getMessage());
                return null;
            }
        }
        try {
            ResponseEntity<String> response = usersClient.getAllUsers();
            return ResponseEntity.status(response.getStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response.getBody());
        }catch (FeignException e){
            return ResponseEntity.status(e.status()).body(e.contentUTF8());
        }catch (Exception e) {
            System.out.println("Gateway error: " + e.getMessage());
            return null;
        }
    }
}
