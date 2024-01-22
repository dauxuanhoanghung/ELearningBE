package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.request.LecturerRegistrationRequest;
import com.dxhh.elearning.dto.request.RejectForm;
import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.pojos.LecturerRegistration;
import com.dxhh.elearning.services.EmailService;
import com.dxhh.elearning.services.LecturerRegistrationService;
import com.dxhh.elearning.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/lecturer-registration/", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LecturerRegistrationController {

    private final LecturerRegistrationService lecturerRegistrationService;
    private final EmailService emailService;
    private final UserRoleService userRoleService;

    @Autowired
    public LecturerRegistrationController(LecturerRegistrationService lecturerRegistrationService, EmailService emailService, UserRoleService userRoleService) {
        this.lecturerRegistrationService = lecturerRegistrationService;
        this.emailService = emailService;
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public ResponseEntity<ModelResponse> getRegistrationForm(@RequestParam Map<String, String> params) {
        List<LecturerRegistration> list = lecturerRegistrationService.getForm(params);
        ModelResponse response = new ModelResponse(HttpStatus.OK.value(), "Success", list);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/current-user")
    public ResponseEntity<ModelResponse> getLecturerFormByCurrentUser() {
        LecturerRegistration registration = lecturerRegistrationService.getByCurrentUser();
        ModelResponse response;
        if (registration != null) {
            response = new ModelResponse(HttpStatus.OK.value(), "Success", registration);
        } else {
            response = new ModelResponse(HttpStatus.NOT_FOUND.value(), "Registration not found", null);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ModelResponse> create(@ModelAttribute LecturerRegistrationRequest request) {
        LecturerRegistration createdRegistration = lecturerRegistrationService.create(request);
        ModelResponse response = new ModelResponse(HttpStatus.CREATED.value(), "Registration created", createdRegistration);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ModelResponse> deleteRegistration(@PathVariable Integer id) {
        lecturerRegistrationService.delete(id);
        ModelResponse response = new ModelResponse(HttpStatus.NO_CONTENT.value(), "Registration deleted", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<ModelResponse> adminAuthorize(@PathVariable Integer id) {
        LecturerRegistration registration = lecturerRegistrationService.getById(id);
        userRoleService.add(registration.getUser());
        if (!registration.getUser().getEmail().isEmpty())
            emailService.sendHtmlEmail(registration.getUser().getEmail(),
                    "Your application for lecturer has been approved",
                "Your application for lecturer has been approved");
        lecturerRegistrationService.delete(id);
        ModelResponse response = new ModelResponse(HttpStatus.NO_CONTENT.value(), "Approve completed", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/reject")
    public ResponseEntity<ModelResponse> adminReject(@RequestBody RejectForm form) {
        LecturerRegistration registration = lecturerRegistrationService.getById(form.getId());
        if (!registration.getUser().getEmail().isEmpty())
            emailService.sendHtmlEmail(registration.getUser().getEmail(),
                    "Your application for lecturer has been rejected",
                    "Your application for lecturer has been rejected. Reason: " + form.getReason());
        lecturerRegistrationService.delete(form.getId());
        ModelResponse response = new ModelResponse(HttpStatus.NO_CONTENT.value(), "Rejected the form", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
