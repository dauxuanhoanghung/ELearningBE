package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.pojos.Progress;
import com.dxhh.elearning.services.ProgressService;
import com.dxhh.elearning.utils.Routing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(Routing.PROGRESS)
public class ProgressController {

    private static final String TAG = "ProgressController";
    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping
    public ResponseEntity<ModelResponse> save() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/get-list/{courseId}")
    public ResponseEntity<ModelResponse> getProgressByUser(@PathVariable Integer courseId) {
        Map<String, String> params = new HashMap<>();
        params.put("courseId", courseId + "");
        List<Progress> progress = progressService.findAll(params);

        ModelResponse response = new ModelResponse(HttpStatus.OK.value(), "Get progress in course for user", null);

        return ResponseEntity.ok(response);
    }

}
