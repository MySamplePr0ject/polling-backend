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
    public String submitNewAnswer (@RequestBody AnswerSet answerSet){
        return answerSetService.submitAnswerSet(answerSet);
    }

    @PutMapping
    public String updatePollResponse (@RequestBody AnswerSet answerSet){
        return answerSetService.updateSet(answerSet);
    }

    @DeleteMapping("/{responseId}")
    public String deletePollResponse (@PathVariable int responseId){
        return answerSetService.deleteResponse(responseId);
    }

    @GetMapping("/user")
    public String getAllUserResponses (@RequestParam int id){
        return answerSetService.getAllUserResponses(id);
    }

    @GetMapping("/question")
    public String getAllQuestionResponses (@RequestParam int id){
        return answerSetService.getAllQuestionResponses(id);
    }
}
