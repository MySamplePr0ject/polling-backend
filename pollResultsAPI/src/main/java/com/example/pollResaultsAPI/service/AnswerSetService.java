package com.example.pollResaultsAPI.service;

import com.example.pollResaultsAPI.QuestionClient;
import com.example.pollResaultsAPI.UserClient;
import com.example.pollResaultsAPI.model.AnswerSet;
import com.example.pollResaultsAPI.model.QuestionDTO;
import com.example.pollResaultsAPI.model.UserDTO;
import com.example.pollResaultsAPI.repository.AnswerSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class AnswerSetService {
    @Autowired
    AnswerSetRepository answerSetRepository;
    @Autowired
    UserClient userClient;
    @Autowired
    QuestionClient questionClient;

    public String submitAnswerSet (AnswerSet answerSet, String email){
        try {
            if (validateUser(answerSet.getUserId(), email)){
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

    public String updateSet (AnswerSet answerSet, String email){
        try {
            if (validateUser(answerSet.getUserId(), email)){
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

    public String deleteResponse (int id, String email){
        try {
            AnswerSet response = getResponseAux(id);
            if (validateUser(response.getUserId(), email)){
                return "You have successfully deleted your response: " + answerSetRepository.delete(id);
            }else {
                return "You are not a registered user. Only registered users may delete answers";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private AnswerSet getResponseAux (int id){
        return answerSetRepository.getResultSetById(id);
    }

    public String getAllUserResponses (int userId, String email){
        try {
            System.out.println("received ID: " + userId + " and mail: " + email);
            if (validateUser(userId, email)){
                List<AnswerSet> userResponses = answerSetRepository.getResultsByUser(userId);
                StringBuilder responseText = new StringBuilder("User " + userId + " have responded as listed:\n");
                userResponses.forEach(row ->
                                responseText.append(findQuestionById(row.getQuestionRef()).getQuestion())
                                        .append(": ")
                                        .append(getAnswerAux(row.getQuestionRef(), row.getAnswerGiven()))
                                        .append("\n")
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

    public String getAllQuestionResponses (int q, String email){
        try {
                List<AnswerSet> userResponses = answerSetRepository.getResultsByQuestion(q);
                QuestionDTO questionDTO = findQuestionById(q);
//                System.out.println(questionDTO);
                StringBuilder responseText = new StringBuilder(
                        "Summary of responses to question " + q +":\n" +
                        questionDTO.getQuestion() + "\n"
                );
                Map<String, Integer> aggregateResults = new HashMap<>(Map.of( "a", 0, "b", 0, "c", 0, "d", 0));
                userResponses.forEach(row -> {
                    String key = String.valueOf(row.getAnswerGiven());
                    aggregateResults.replace(key, aggregateResults.get(key) + 1);
                });
                responseText.append(questionDTO.getAnswerA()).append(": ").append(aggregateResults.get("a")).append("\n")
                        .append(questionDTO.getAnswerB()).append(": ").append(aggregateResults.get("b")).append("\n")
                        .append(questionDTO.getAnswerC()).append(": ").append(aggregateResults.get("c")).append("\n")
                        .append(questionDTO.getAnswerD()).append(": ").append(aggregateResults.get("d"));
               return responseText.toString();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean validateUser (int id, String email){
        //verify user E-mail in users DB
        UserDTO user = findUserById(id);
//        System.out.println("validating " + email + " in front of\n" + user.getEmail());
        if (email.equals(user.getEmail())) {
            System.out.println("User successfully validated");
            return true;
        } else {
            System.out.println("Provided E-mail is not in DB");
            return false;
        }
    }

    private UserDTO findUserById (int id){
        return userClient.getUserDetails(id);
    }

    private QuestionDTO findQuestionById (int id){
//        System.out.println("Querying q. " + id + "\nResult is: " + questionClient.getQuestionDetails(id));
        return questionClient.getQuestionDetails(id);
    }

    private String getAnswerAux (int questionId, char ans){
        QuestionDTO questionDTO = questionClient.getQuestionDetails(questionId);
        switch (ans){
            case 'a':
                return questionDTO.getAnswerA();
            case 'b':
                return questionDTO.getAnswerB();
            case 'c':
                return questionDTO.getAnswerC();
            case 'd':
                return questionDTO.getAnswerD();
            default:
                throw new IllegalStateException("Unexpected value: " + ans);
        }
    }

    //Handles answers deletion upon user deletion.
    public String deleteUserResponses (int id, String auth){
        if ("Th!$^I$^C0rrect".equals(auth)){
            try{
                UserDTO deletedUser = findUserById(id);
                List<AnswerSet> deletedResponses = answerSetRepository.deleteAllUserAnswers(id);
                return "Total of " + deletedResponses.size() + " responses from user " + deletedUser.getName() + " " + deletedUser.getSurname() + " were deleted";
            }catch (NullPointerException e){
                throw new NullPointerException("User does not exist");
            }catch (Exception e){
                System.out.println("exception caught in response : " + e);
                return null;
            }
        }else {
            return "Illegal action. this incident will be reported";
        }
    }
}
