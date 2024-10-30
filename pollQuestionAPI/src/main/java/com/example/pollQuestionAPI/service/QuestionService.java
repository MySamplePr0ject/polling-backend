package com.example.pollQuestionAPI.service;

import com.example.pollQuestionAPI.model.Question;
import com.example.pollQuestionAPI.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    public String createQuestion (Question question){
        try {
            Question createdQuestion = questionRepository.save(question);
            return "Question no. " + createdQuestion.getId() + " was successfully created\n" + createdQuestion;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public String updateQuestion (Question question){
        try {
            return "Question was successfully updated:\n" + questionRepository.update(question);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public String deleteQuestionByID (int id){
        try {
            Question deletedQuestion = questionRepository.deleteByID(id);
            return "Question " + id + " was successfully deleted and will no longer appear in polls.\n" + deletedQuestion;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public Question getQuestionByID(int id){
        try {
            return questionRepository.getByID(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public List<Question> getAllQuestions(){
        try {
            return questionRepository.getAllQuestions();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public void validateQuestion(Question question){

    }
}
