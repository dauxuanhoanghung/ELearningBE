package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.utils.Routing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(Routing.PROGRESS)
public class ProgressController {

    public ProgressController() {
    }

    @PostMapping
    public ResponseEntity<ModelResponse> save() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/get-list")
    public ResponseEntity<ModelResponse> getProgressByUser() {
        ModelResponse response = new ModelResponse(HttpStatus.OK.value(), "Get progress in course for user", null);

        return ResponseEntity.ok(response);
    }

}
