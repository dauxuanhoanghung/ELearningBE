package com.dxhh.elearning.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/")
public class UserController {

    @GetMapping
    public ResponseEntity hi() {
        return ResponseEntity.ok("Hi");
    }
}
