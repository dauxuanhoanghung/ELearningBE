package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.request.UserRegisterRequest;
import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.dto.response.UserResponse;
import com.dxhh.elearning.mappers.UserMapper;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.services.EmailService;
import com.dxhh.elearning.services.UserService;
import com.dxhh.elearning.utils.Routing;
import com.dxhh.elearning.validators.ExistingUsernameValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = Routing.USERS, produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;
    private final EmailService emailService;
    private final ExistingUsernameValidator usernameValidator;

    @Autowired
    public UserController(UserService userService,
                          UserMapper mapper,
                          EmailService emailService,
                          ExistingUsernameValidator usernameValidator) {
        this.userService = userService;
        this.mapper = mapper;
        this.emailService = emailService;
        this.usernameValidator = usernameValidator;
    }

    @GetMapping
    public ResponseEntity<ModelResponse> findAll(@RequestParam Map<String, String> params) {
        List<User> users = userService.findAll(params);
        ModelResponse response = ModelResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Get list users successful")
                .data(users.stream().map(mapper::toResponse).toList())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/count")
    public ResponseEntity<ModelResponse> count(@RequestParam Map<String, String> params) {
        Integer count = userService.count(params);
        return ResponseEntity.ok(new ModelResponse(200, "Count user", count));
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
                res.setData(mapper.toResponse(userService.findByUsername(username).get(0)));
                return ResponseEntity.ok(res);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/top-lecturers")
    public ResponseEntity<ModelResponse> getTopLecturers(@RequestParam(required = false, defaultValue = "5") int top) {
        List<User> users = userService.getTopLecturers(top);
        List<UserResponse> userResponses = users.stream().map(mapper::toResponse).toList();
        ModelResponse response = new ModelResponse(200, "Top lecturers", userResponses);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update-info", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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

    @DeleteMapping("/{id}")
    public ResponseEntity<ModelResponse> deleteById(@PathVariable("id") Integer id) {
        ModelResponse response;
        if (userService.deleteById(id)) {
            response = new ModelResponse(204, "Deleted user successful", null);
        } else {
            response = new ModelResponse(404, "User not found", null);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<ModelResponse> deleteCurrent(@RequestPart("password") String password) {
        ModelResponse response;
        if (userService.deleteCurrent(password)) {
            response = new ModelResponse(204, "Deleted user successful", null);
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
            return ResponseEntity.ok(resetPwUrl);
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

    @PostMapping("/payout-credit")
    public ResponseEntity<ModelResponse> payout(@RequestBody Map<String, String> params) {
        Double credit = Double.parseDouble(params.get("credit"));
        credit = userService.findByUsername(params.get("username")).get(0).getCredit() - credit;
        User user = userService.updateCreditByUsername(params.get("username"), credit);
        if (user != null) {
            return ResponseEntity.ok(new ModelResponse(200, "Change password successful", mapper.toResponse(user)));
        } else {
            return ResponseEntity.ok(new ModelResponse(400, "Change password failed", null));
        }
    }
}
