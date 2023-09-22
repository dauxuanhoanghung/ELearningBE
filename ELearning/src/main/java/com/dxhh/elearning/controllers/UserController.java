package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.request.UserRegisterRequest;
import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.mappers.UserMapper;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users/", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService; // You should have a UserService to handle user-related logic
    private final UserMapper mapper;
    @Autowired
    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }
    @GetMapping
    public ResponseEntity hi() {
        return ResponseEntity.ok("Hi");
    }

    @PostMapping
    public ResponseEntity<?> create(@ModelAttribute UserRegisterRequest userRequest, BindingResult rs) {
        ModelResponse response = new ModelResponse();

        if (rs.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation errors");
        }

        User user = userService.save(userRequest);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User creation failed");
        }
        response.setStatus(201);
        response.setData(mapper.toResponse(user));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
