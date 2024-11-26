package com.example.APIGateway.controller;

import com.example.APIGateway.QuestionsClient;
import com.example.APIGateway.ResultsClient;
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
    @Autowired
    QuestionsClient questionsClient;
    @Autowired
    ResultsClient resultsClient;

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

    @PostMapping("/questions")
    public ResponseEntity<String> addQuestion (@RequestBody String questionJson){
        try{
            ResponseEntity<String> response = questionsClient.addNewQuestion(questionJson);
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

    @PutMapping("/questions")
    public ResponseEntity<String> updateQuestion (@RequestBody String questionJson){
        try {
            ResponseEntity<String> response = questionsClient.updateQuestion(questionJson);
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
    @DeleteMapping("/questions/{id}")
    public ResponseEntity<String> deleteQuestion (@PathVariable int id){
        try {
            ResponseEntity<String> response = questionsClient.deleteQuestion(id);
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

    @GetMapping({"/questions/", "/questions", "/questions/{id}"})
    public ResponseEntity<String> getQuestions (@PathVariable(required = false) Integer id){
        if (id != null){
            try {
                ResponseEntity<String> response = questionsClient.getQuestionById(id);
                return ResponseEntity.status(response.getStatusCode())
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(response.getBody());
            }catch (RetryableException e){
                System.out.println("Server is down. Notify on-call guy" + e);
                return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("We are experiencing difficulties, but don't worry, we'll have it fixed in no time");
            }catch (FeignException e){
                return ResponseEntity.status(e.status()).body(e.contentUTF8());
            }catch (Exception e){
                System.out.println("Gateway error: " + e.getMessage());
                return null;
            }
        }
        try {
            ResponseEntity<String> response = questionsClient.getAllQuestions();
            return ResponseEntity.status(response.getStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response.getBody());
        }catch (RetryableException e){
            System.out.println("Server is down. Notify on-call guy" + e);
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("We are experiencing difficulties, but don't worry, we'll have it fixed in no time");
        }catch (FeignException e){
            return ResponseEntity.status(e.status()).body(e.contentUTF8());
        }catch (Exception e) {
            System.out.println("Gateway error: " + e.getMessage());
            return null;
        }
    }

    @PostMapping("/responses")
    public ResponseEntity<String> submitResponse (@RequestBody String answerSet, @RequestHeader("email") String email){
        try{
            ResponseEntity<String> response = questionsClient.addNewQuestion(answerSet);
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

    @PutMapping("/responses")
    public ResponseEntity<String> updateResponse (@RequestBody String answerSet, @RequestHeader("email") String email){
        try {
            ResponseEntity<String> response = resultsClient.updateResponse(answerSet);
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
    @DeleteMapping("/responses/{id}")
    public ResponseEntity<String> deleteResponse (@PathVariable int id, @RequestHeader("email") String email){
        try {
            ResponseEntity<String> response = resultsClient.deleteResponse(id);
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

    @GetMapping("/responses/user")
    public ResponseEntity<String> getResponsesPerUser (@RequestParam int id, @RequestHeader("email") String email){
        try {
            ResponseEntity<String> response = resultsClient.getResponsesPerUser(id, email);
            return ResponseEntity.status(response.getStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response.getBody());
        }catch (RetryableException e){
            System.out.println("Server is down. Notify on-call guy" + e);
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("We are experiencing difficulties, but don't worry, we'll have it fixed in no time");
        }catch (FeignException e){
            return ResponseEntity.status(e.status()).body(e.contentUTF8());
        }catch (Exception e){
            System.out.println("Gateway error: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/responses/question")
    public ResponseEntity<String> getResponsesPerQusetion (@RequestParam int id, @RequestHeader("email") String email){
        try {
            ResponseEntity<String> response = resultsClient.getResponsePerQuestion(id, email);
            return ResponseEntity.status(response.getStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response.getBody());
        }catch (RetryableException e){
            System.out.println("Server is down. Notify on-call guy" + e);
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("We are experiencing difficulties, but don't worry, we'll have it fixed in no time");
        }catch (FeignException e){
            return ResponseEntity.status(e.status()).body(e.contentUTF8());
        }catch (Exception e){
            System.out.println("Gateway error: " + e.getMessage());
            return null;
        }
    }
}
