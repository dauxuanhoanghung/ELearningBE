package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.request.UserCredentialRequest;
import com.dxhh.elearning.dto.request.UserRegisterRequest;
import com.dxhh.elearning.dto.response.ErrorResponse;
import com.dxhh.elearning.dto.response.JwtResponse;
import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.jwt.JwtTokenUtils;
import com.dxhh.elearning.mappers.UserMapper;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.services.UserService;
import com.dxhh.elearning.utils.Routing;
import com.dxhh.elearning.validators.ExistingUsernameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(value = Routing.AUTH, produces = {MediaType.APPLICATION_JSON_VALUE})
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;
    private final ExistingUsernameValidator usernameValidator;
    private final UserMapper mapper;

    @Autowired
    public AuthController(UserService userService,
                          AuthenticationManager authenticationManager,
                          JwtTokenUtils jwtTokenUtils,
                          ExistingUsernameValidator usernameValidator,
                          UserMapper mapper) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtils = jwtTokenUtils;
        this.usernameValidator = usernameValidator;
        this.mapper = mapper;
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
        } else
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

    @PostMapping("/register")
    public ResponseEntity<?> register(@ModelAttribute UserRegisterRequest userRequest, BindingResult rs) {
        usernameValidator.validate(userRequest, rs);
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
