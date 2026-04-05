package com.garmentsystem.crm.controller;

import com.garmentsystem.crm.model.User;
import com.garmentsystem.crm.repository.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserRepository userRepository;

    //get all
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //get user by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("user not found");
        }else {
            return userRepository.findById(id).get();
        }

    }

    //patch user by id
    @PatchMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("user not found");
        }else {
            User existingUser = userRepository.findById(id).get();
            if (user.getName() != null) existingUser.setName(user.getName());
            if (user.getEmail() != null) existingUser.setEmail(user.getEmail());
            if (user.getPhone() != null) existingUser.setPhone(user.getPhone());
            if (user.getRole() != null) existingUser.setRole(user.getRole());
            if (user.getPosition() != null) existingUser.setPosition(user.getPosition());
            return userRepository.save(existingUser);
        }
    }

    //delete user by id
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("user not found");
        }else {
            userRepository.deleteById(id);
        }
    }
}

