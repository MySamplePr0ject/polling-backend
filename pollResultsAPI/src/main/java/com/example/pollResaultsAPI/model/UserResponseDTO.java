package com.example.pollResaultsAPI.model;

import java.util.List;

public class UserResponseDTO {
    private UserDTO user;
    private List<AnswerSet> userResponses;

    public UserResponseDTO() {
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<AnswerSet> getUserResponses() {
        return userResponses;
    }

    public void setUserResponses(List<AnswerSet> userResponses) {
        this.userResponses = userResponses;
    }

    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "user=" + user +
                ", userResponses=" + userResponses +
                '}';
    }
}
