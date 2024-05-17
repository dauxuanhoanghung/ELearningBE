package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.services.UserService;
import com.dxhh.elearning.utils.Routing;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = Routing.SELF, produces = MediaType.APPLICATION_JSON_VALUE)
public class SelfController {

    private final UserService userService;

    public SelfController(UserService userService) {
        this.userService = userService;
    }


    @DeleteMapping("/delete-account")
    public ResponseEntity<ModelResponse> deleteAccount() {
        return null;
    }

    @PostMapping("/change-password")
    public ResponseEntity<ModelResponse> changePassword(@RequestBody Map<String, String> request) {
        String password = request.get("currentPassword");
        String newPassword = request.get("newPassword");
        User user = userService.changePassword(password, newPassword);
        ModelResponse response = ModelResponse.builder()
                .status(200)
                .message("Change password successful")
                .data(user)
                .build();
        if (user == null) {
            response.setStatus(400);
            response.setMessage("Change password failed");
        }
        return ResponseEntity.ok(response);
    }
}
