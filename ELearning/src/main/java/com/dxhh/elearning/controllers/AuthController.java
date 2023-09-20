package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.request.UserCredentialRequest;
import com.dxhh.elearning.dto.response.ErrorResponse;
import com.dxhh.elearning.dto.response.JwtResponse;
import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.jwt.JwtTokenUtils;
import com.dxhh.elearning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/api/auth/", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;
    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtils) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtils = jwtTokenUtils;
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody UserCredentialRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        final UserDetails userDetails = userService
                .loadUserByUsername(request.getUsername());
        if (userDetails != null) {
            final String token = jwtTokenUtils.generateToken(userDetails);
            final Date expirationDate = this.jwtTokenUtils.getExpirationDateFromToken(token);
            String ex = expirationDate.toString();
            return ResponseEntity.ok(new ModelResponse(200, "Auth Successful", new JwtResponse(token, ex)));
        }
        else
            return ResponseEntity.status(400).body(new ErrorResponse());
    }
}
