package com.example.pollQuestionAPI.controller;

import com.example.pollQuestionAPI.model.Question;
import com.example.pollQuestionAPI.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @PostMapping
    public ResponseEntity<String> createQuestion (@RequestBody Question question){
        try {
            isValid(question);
            return new ResponseEntity<>(questionService.createQuestion(question), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
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

    private void isValid (Question question){
        if (question.getQuestion() == null ||
                question.getAnswerA() == null ||
                question.getAnswerB() == null ||
                question.getAnswerC() == null ||
                question.getAnswerD() == null){
            throw new DataIntegrityViolationException("Missing parameter");
        }
    }
}
