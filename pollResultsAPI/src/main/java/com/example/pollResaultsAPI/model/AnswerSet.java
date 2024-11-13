package com.example.pollResaultsAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnswerSet {
    private int id;
    @JsonProperty("question_ref")
    private int questionRef;
    @JsonProperty("answer")
    private char answerGiven;
    @JsonProperty("user_id")
    private int userId;
    private String email;
    public AnswerSet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionRef() {
        return questionRef;
    }

    public void setQuestionRef(int questionRef) {
        this.questionRef = questionRef;
    }

    public char getAnswerGiven() {
        return answerGiven;
    }

    public void setAnswerGiven(char answerGiven) {
        this.answerGiven = answerGiven;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AnswerSet{" +
                "id=" + id +
                ", questionRef=" + questionRef +
                ", answerGiven=" + answerGiven +
                ", userId=" + userId +
                '}';
    }
}
