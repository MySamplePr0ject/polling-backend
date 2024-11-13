package com.example.pollResaultsAPI.service;

import com.example.pollResaultsAPI.model.AnswerSet;
import com.example.pollResaultsAPI.repository.AnswerSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnswerSetService {
    @Autowired
    AnswerSetRepository answerSetRepository;

    public String submitAnswerSet (AnswerSet answerSet){
        try {
            if (validateUser("")){
                AnswerSet submittedAnswer = answerSetRepository.save(answerSet);
                return "You have answered question no. " + submittedAnswer.getQuestionRef() + ". Your answer is: " + submittedAnswer.getAnswerGiven();
            }else {
                return "You are not a registered user. Only registered users may submit answers";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String updateSet (AnswerSet answerSet){
        try {
            if (validateUser("")){
                AnswerSet updatedSet = answerSetRepository.update(answerSet);
                return "You have successfully update your response to question " + answerSet.getQuestionRef() +
                        "\nYour previous response was: " + answerSet +
                        "\nYour new response is: " + updatedSet;
            }else {
                return "You are not a registered user. Only registered users may update answers";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String deleteResponse (int id){
        try {
            if (validateUser("")){
                return "You have successfully deleted your response: " + answerSetRepository.delete(id);
            }else {
                return "You are not a registered user. Only registered users may delete answers";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String getAllUserResponses (int userId){
        try {
            if (validateUser("")){
                List<AnswerSet> userResponses = answerSetRepository.getResultsByUser(userId);
                StringBuilder responseText = new StringBuilder("User " + userId + " have responded as listed:");
                userResponses.forEach(row ->
                                responseText.append(row.getQuestionRef()).append(": ").append(row.getAnswerGiven()).append("\n")
                        );
                return responseText.toString();
            }else{
                return "You are not a registered user. Only registered users may view answers";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String getAllQuestionResponses (int q){
        try {
            if (validateUser("")){
                List<AnswerSet> userResponses = answerSetRepository.getResultsByQuestion(q);

                StringBuilder responseText = new StringBuilder(
                        "The following users responded to question " + q +":\n" +
                        findQuestionById(q)
                );
                Map<String, Integer> aggregateResults = new HashMap<>(Map.of("a", 0, "b", 0, "c", 0, "d", 0));
                userResponses.forEach(row -> {
                    String key = String.valueOf(row.getAnswerGiven());
                    aggregateResults.replace(key, aggregateResults.get(key) + 1);
                });
                responseText.append(aggregateResults);
               return responseText.toString();
            }else{
                return "You are not a registered user. Only registered users may view answers";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    public boolean validateUser (String email){
        //verify user E-mail in users DB
        return true;
    }

    private String findUserNameById (int id){
        return "MOCK user";
    }

    private String findQuestionById (int id){
        return "MOCK question";
    }
}
