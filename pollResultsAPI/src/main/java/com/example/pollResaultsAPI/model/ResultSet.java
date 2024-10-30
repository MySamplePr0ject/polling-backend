package com.example.pollResaultsAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultSet {
    int id;
    @JsonProperty("question_ref")
    int questionRef;
    @JsonProperty("answer")
    char answerGiven;
    @JsonProperty("user_id")
    int userId;

    public ResultSet() {
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

    public void setAnswerGiven(String answerGiven) {
        this.answerGiven = answerGiven;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ResultSet{" +
                "id=" + id +
                ", questionRef=" + questionRef +
                ", answerGiven=" + answerGiven +
                ", userId=" + userId +
                '}';
    }
}
