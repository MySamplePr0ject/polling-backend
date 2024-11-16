package com.example.pollResaultsAPI.controller;

import com.example.pollResaultsAPI.model.AnswerSet;
import com.example.pollResaultsAPI.service.AnswerSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AnswerSetController {
    @Autowired
    AnswerSetService answerSetService;
    @PostMapping
    public String submitNewAnswer (@RequestBody AnswerSet answerSet, @RequestHeader("email") String email){
        return answerSetService.submitAnswerSet(answerSet, email);
    }

    @PutMapping
    public String updatePollResponse (@RequestBody AnswerSet answerSet, @RequestHeader("email") String email){
        return answerSetService.updateSet(answerSet, email);
    }

    @DeleteMapping("/{responseId}")
    public String deletePollResponse (@PathVariable int responseId, @RequestHeader("email") String email){
        return answerSetService.deleteResponse(responseId, email);
    }

    @GetMapping("/user")
    public String getAllUserResponses (@RequestParam int id, @RequestHeader("email") String email){
        return answerSetService.getAllUserResponses(id, email);
    }

    @GetMapping("/question")
    public String getAllQuestionResponses (@RequestParam int id, @RequestHeader("email") String email){
        return answerSetService.getAllQuestionResponses(id, email);
    }

    //User deletion handler
    @DeleteMapping ("/deleteUser/{id}")
    public String deleteUser (@PathVariable int id, @RequestHeader("auth") String phrase){
        return answerSetService.deleteUserResponses(id, phrase);
    }
}
