package com.example.APIGateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "usersAPI", url = "${usersAPI.url}")
public interface UsersClient {
    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<String> register (@RequestBody String UserJson);

    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<String> update (@RequestBody String userJson);

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable int id);

    @GetMapping("/{id}")
    ResponseEntity<String> getUserById (@PathVariable Integer id);

    @GetMapping("/users")
    ResponseEntity<String> getAllUsers ();
}






//@FeignClient(name = "resultsAPI", url = "${resultsAPI.url}")
//public interface ResultsClient {
//    @DeleteMapping("/deleteUser/{id}")
//    String deleteUserResponses (@PathVariable("id") int id, @RequestHeader("auth") String phrase);
//}