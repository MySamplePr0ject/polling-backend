package com.example.usersAPI.service;

import com.example.usersAPI.model.User;
import com.example.usersAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService {
    //Patterns for validation
    private static final Pattern NAME_SURNAME_PATTERN = Pattern.compile("^[a-zA-Z]+$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern ADDRESS_PATTERN = Pattern.compile("^[a-zA-Z0-9., ]*$");

    @Autowired
    UserRepository userRepository;

    public String createUser (User user){
        this.validateUser(user);
//        System.out.println("Received user: "+ user);
        User createdUser = userRepository.save(user);
//        System.out.println("createdUser: " + createdUser);
        return "User " + createdUser.getName() + " " + createdUser.getSurname() + " was successfully created";
    }

    public String updateUser (User user){
        User updatedUser = userRepository.update(user);
        return "The following user was successfully updated:\n";
    }

    public String deleteUserByID (int id){
        User deletedUser = userRepository.deleteById(id);
        return deletedUser.getName() + " " + deletedUser.getSurname()+ " was successfully deleted.";
    }

    public User getUserByID (int id){
        return userRepository.getUserById(id);
    }

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
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

        if (user.getAge() < 0) { // Assuming age cannot be negative
            throw new IllegalArgumentException("Age must be a positive integer and cannot be null");
        }

        if (user.getAddress() != null && !ADDRESS_PATTERN.matcher(user.getAddress()).matches()) {
            throw new IllegalArgumentException("Address must contain letters, numbers, periods (.) and commas (,) only");
        }
    }
}
