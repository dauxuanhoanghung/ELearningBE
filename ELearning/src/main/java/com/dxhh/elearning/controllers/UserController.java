package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.request.UserRegisterRequest;
import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.mappers.UserMapper;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.services.EmailService;
import com.dxhh.elearning.services.UserService;
import com.dxhh.elearning.utils.Routing;
import com.dxhh.elearning.validators.ExistingUsernameValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = Routing.USERS, produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;
    private final EmailService emailService;
    private final ExistingUsernameValidator usernameValidator;

    @Autowired
    public UserController(UserService userService, UserMapper mapper, EmailService emailService, ExistingUsernameValidator usernameValidator) {
        this.userService = userService;
        this.mapper = mapper;
        this.emailService = emailService;
        this.usernameValidator = usernameValidator;
    }

    @GetMapping("/current-user")
    public ResponseEntity<ModelResponse> getMyAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                ModelResponse res = new ModelResponse();
                res.setStatus(200);
                res.setData(mapper.toResponse(userService.getUserByUsername(username).get(0)));
                return ResponseEntity.ok(res);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping
    public ResponseEntity<?> create(@ModelAttribute UserRegisterRequest userRequest, BindingResult rs) {
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

    @PutMapping("/update-info")
    public ResponseEntity<ModelResponse> updateInfo(@ModelAttribute UserRegisterRequest userRegisterRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        ModelResponse response;
        User user = userService.findOneByUsername(username);
        User updatedUser = userService.update(user, userRegisterRequest);
        if (user != null) {
            response = new ModelResponse(200, "Updated user successful", mapper.toResponse(updatedUser));
        } else {
            response = new ModelResponse(404, "User not found", null);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> processForgotPassword(HttpServletRequest request, @RequestParam final String email) {
        String token = RandomString.make(45);
        try {
//            userService.updateResetPassword(token, email);
            String resetPwUrl = "/reset-password?token=" + token;

            sendMail(email, resetPwUrl);
            return ResponseEntity.ok().body(resetPwUrl);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Resource not found with email " + email);
        }
    }

    public void sendMail(final String email, final String resetPasswordLink) {
        String subject = "Here is the link to reset your password";
        String content = "<p>Hello,</p>" +
                "<p>You have request to reset your password.</p>"
                + "<p>Click link below to change your password:</p>"
                + "<p><b><a href=\"" + resetPasswordLink + "\">Change my password</a></b></p>"
                + "<p>Ignore this email if you do remember your password, or you have not make a request</p>";

        emailService.sendHtmlEmail(email, subject, content);
    }
}
