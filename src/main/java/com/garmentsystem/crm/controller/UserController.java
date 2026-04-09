package com.garmentsystem.crm.controller;

import com.garmentsystem.crm.dto.WorkerSummaryDTO;
import com.garmentsystem.crm.model.Role;
import com.garmentsystem.crm.model.User;
import com.garmentsystem.crm.repository.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

public class UserController {

    private final UserRepository userRepository;

    //get all
    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //get user by id
    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    }

    //patch user by id
    @PatchMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (user.getName() != null) existingUser.setName(user.getName());
        if (user.getEmail() != null) existingUser.setEmail(user.getEmail());
        if (user.getPhone() != null) existingUser.setPhone(user.getPhone());
        if (user.getRole() != null) existingUser.setRole(user.getRole());
        if (user.getPosition() != null) existingUser.setPosition(user.getPosition());
        return userRepository.save(existingUser);
    }

    //delete user by id
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //get user == worker
    @GetMapping("/getworker")
    @SecurityRequirement(name = "bearerAuth")
    public List<WorkerSummaryDTO> getWorkers() {
        return userRepository.findByRole(Role.WORKER).stream()
                .map(u -> new WorkerSummaryDTO(u.getId(), u.getName()))
                .toList();
    }
}

