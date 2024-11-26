package com.example.APIGateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "questionAPI", url = "${questionAPI.url}")
public interface QuestionsClient {
    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<String> addNewQuestion (@RequestBody String questionJson);

    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<String> updateQuestion (@RequestBody String questionJson);

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteQuestion (@PathVariable int id);

    @GetMapping("/{id}")
    ResponseEntity<String> getQuestionById (@PathVariable int id);

    @GetMapping("/questions")
    ResponseEntity<String> getAllQuestions ();
}
