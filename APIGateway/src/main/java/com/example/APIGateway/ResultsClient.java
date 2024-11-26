package com.example.APIGateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "resultsAPI", url = "${resultsAPI.url}")
public interface ResultsClient {
    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<String> SubmitResponse (@RequestBody String questionJson);

    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<String> updateResponse (@RequestBody String questionJson);

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteResponse (@PathVariable int id);

    @GetMapping("/user")
    ResponseEntity<String> getResponsesPerUser (@RequestParam int id, @RequestHeader("email") String email);

    @GetMapping("/question")
    ResponseEntity<String> getResponsePerQuestion (@RequestParam int id, @RequestHeader("email") String email);
}
