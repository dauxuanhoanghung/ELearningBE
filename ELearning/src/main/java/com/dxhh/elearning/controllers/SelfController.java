package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.utils.Routing;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = Routing.SELF, produces = MediaType.APPLICATION_JSON_VALUE)
public class SelfController {
    @DeleteMapping("/delete-account")
    public ResponseEntity<ModelResponse> deleteAccount() {
        return null;
    }
}
