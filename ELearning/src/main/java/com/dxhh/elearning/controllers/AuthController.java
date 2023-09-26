package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.request.IdTokenRequest;
import com.dxhh.elearning.dto.request.UserCredentialRequest;
import com.dxhh.elearning.dto.request.UserRegisterRequest;
import com.dxhh.elearning.dto.response.ErrorResponse;
import com.dxhh.elearning.dto.response.JwtResponse;
import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.jwt.JwtTokenUtils;
import com.dxhh.elearning.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    //handles google authentication callback
//    @PostMapping("/login-google")
//    public ResponseEntity LoginWithGoogleOauth2(@RequestBody IdTokenRequest requestBody, HttpServletResponse response) {
//        String authToken = userService.loginOAuthGoogle(requestBody);
//        final ResponseCookie cookie = ResponseCookie.from("AUTH-TOKEN", authToken)
//                .httpOnly(true)
//                .maxAge(7 * 24 * 3600)
//                .path("/")
//                .secure(false)
//                .build();
//        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
//        return ResponseEntity.ok().build();
//    }
    @PostMapping("/login-google")
    public ResponseEntity<ModelResponse> loginGoogle(@ModelAttribute UserRegisterRequest request) {
        ModelResponse res = new ModelResponse();
        if (!userService.existsByUsername(request.getUsername())) {
            userService.save(request);
        }
        final UserDetails userDetails = userService
                .loadUserByUsername(request.getUsername());
        if (userDetails != null) {
            final String token = jwtTokenUtils.generateToken(userDetails);
            final Date expirationDate = this.jwtTokenUtils.getExpirationDateFromToken(token);
            String ex = expirationDate.toString();
            return ResponseEntity.ok(new ModelResponse(200, "Auth Successful", new JwtResponse(token, ex)));
        }
        return ResponseEntity.ok(res);
    }
}
