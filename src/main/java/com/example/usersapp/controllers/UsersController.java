package com.example.usersapp.controllers;

import com.example.usersapp.models.User;
import com.example.usersapp.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    public User findUserById(@PathVariable Long userId) throws NotFoundException {
        User foundUser = userRepository.findOne(userId);

        if(foundUser == null) {
            throw new NotFoundException("User with ID of " + userId + " was not found!");
        }
        return foundUser;
    }

    @PostMapping("/users")
    public User createNewUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @PatchMapping("/users/{userId}")
    public User updateUserById(@PathVariable Long userId, @RequestBody User userRequest) {
        User userFromDb = userRepository.findOne(userId);

        userFromDb.setUserName(userRequest.getUserName());
        userFromDb.setFirstName(userRequest.getFirstName());
        userFromDb.setLastName(userRequest.getLastName());

        return userRepository.save(userFromDb);
    }

    @DeleteMapping("/users/{userId}")
    public HttpStatus deleteUserById(@PathVariable Long userId) {
        userRepository.delete(userId);
        return HttpStatus.OK;
    }

    @ExceptionHandler
    void handleUserNotFound(
            NotFoundException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

}
