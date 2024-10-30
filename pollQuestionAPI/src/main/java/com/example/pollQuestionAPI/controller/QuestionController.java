package com.example.pollQuestionAPI.controller;

import com.example.pollQuestionAPI.model.Question;
import com.example.pollQuestionAPI.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @PostMapping
    public String createQuestion (@RequestBody Question question){
        return questionService.createQuestion(question);
    }

    @PutMapping
    public String updateQuestion (@RequestBody Question question){
        return questionService.updateQuestion(question);
    }

    @DeleteMapping("/{id}")
    public String deleteQuestion (@PathVariable int id){
        return questionService.deleteQuestionByID(id);
    }

    @GetMapping("/{id}")
    public Question getQuestionByID (@PathVariable int id){
        return questionService.getQuestionByID(id);
    }

    @GetMapping("/questions")
    public List<Question> getAllQuestions (){
        return questionService.getAllQuestions();
    }
}
