package com.example.usersAPI.service;

import com.example.usersAPI.ResultsClient;
import com.example.usersAPI.model.User;
import com.example.usersAPI.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.RetryableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class UserService {
    //Patterns for validation
    private static final Pattern NAME_SURNAME_PATTERN = Pattern.compile("^[a-zA-Z]{2,}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9.-_+]{2,}@[A-Za-z0-9]{2,}\\.[A-Za-z.]{2,6}");
    private static final Pattern ADDRESS_PATTERN = Pattern.compile("^[a-zA-Z0-9-., ]{5,50}$");

    @Autowired
    UserRepository userRepository;
    @Autowired
    ResultsClient resultsClient;
    @Autowired
    ObjectMapper objectMapper;
    public String createUser (User user) throws JsonProcessingException {
        this.validateUser(user);
        return objectMapper.writeValueAsString(userRepository.save(user));
    }

    public String updateUser (User user) throws ClassNotFoundException {
        if (this.isUserExist(user.getId())){
            this.validateUser(user);
            User updatedUser = userRepository.update(user);
            return "The following user was successfully updated:\n" + updatedUser;
        }else {
            throw new ClassNotFoundException("User doesn't exist");
        }
    }

    public String deleteUserByID (int id){
        try {
            String deletedResponses = resultsClient.deleteUserResponses(id, "Th!$^I$^C0rrect");
            User deletedUser = userRepository.deleteById(id);
            return deletedUser.getName() + " " + deletedUser.getSurname() + " was successfully deleted.\n" +
                    deletedResponses + " responses from this user were deleted.";
        }catch (RetryableException e){
            throw e;
        }catch (Exception e){
            System.out.println("Exception caught: " + e);
            return null;
        }
    }

    public String getUserByID (int id) throws ClassNotFoundException, JsonProcessingException {
        if(!this.isUserExist(id)){
            throw new ClassNotFoundException("User not found");
        }else {
            return objectMapper.writeValueAsString(userRepository.getUserById(id));
        }
    }

    public String getAllUsers() throws JsonProcessingException {
        return objectMapper.writeValueAsString(userRepository.getAllUsers());
    }

    public void validateUser(User user) {

        if (user.getName() == null || !NAME_SURNAME_PATTERN.matcher(user.getName()).matches()) {
            throw new IllegalArgumentException("Name must contain letters only and cannot be null");
        }

        if (user.getSurname() == null || !NAME_SURNAME_PATTERN.matcher(user.getSurname()).matches()) {
            throw new IllegalArgumentException("Surname must contain letters only and cannot be null");
        }

        if (user.getEmail() == null || !EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            throw new IllegalArgumentException("Email must be a valid format and cannot be null");
        }

        if (user.getAge() <= 0 ) { // Assuming age cannot be negative
            throw new IllegalArgumentException("Age must be a positive integer and cannot be null");
        }

        if (user.getAddress() != null && !ADDRESS_PATTERN.matcher(user.getAddress()).matches()) {
            throw new IllegalArgumentException("Address must contain letters, numbers, periods (.) and commas (,) only");
        }
    }

    private boolean isUserExist (int id){
        if (userRepository.getUserById(id) != null){
            return true;
        }else {
            return false;
        }
    }
}
