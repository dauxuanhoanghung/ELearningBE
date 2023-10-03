package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.pojos.LecturerRegistration;
import com.dxhh.elearning.services.LecturerRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/lecturer-registration/", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LecturerRegistrationController {

    private final LecturerRegistrationService lecturerRegistrationService;

    @Autowired
    public LecturerRegistrationController(LecturerRegistrationService lecturerRegistrationService) {
        this.lecturerRegistrationService = lecturerRegistrationService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<ModelResponse> getRegistrationById(@PathVariable Integer id) {
        LecturerRegistration registration = lecturerRegistrationService.getById(id);
        if (registration != null) {
            ModelResponse response = new ModelResponse(HttpStatus.OK.value(), "Success", registration);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ModelResponse response = new ModelResponse(HttpStatus.NOT_FOUND.value(), "Registration not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ModelResponse> create(@RequestBody LecturerRegistration registration) {
        LecturerRegistration createdRegistration = lecturerRegistrationService.create(registration);
        ModelResponse response = new ModelResponse(HttpStatus.CREATED.value(), "Registration created", createdRegistration);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ModelResponse> deleteRegistration(@PathVariable Integer id) {
        lecturerRegistrationService.delete(id);
        ModelResponse response = new ModelResponse(HttpStatus.NO_CONTENT.value(), "Registration deleted", null);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

}
