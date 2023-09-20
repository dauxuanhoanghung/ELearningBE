package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.response.ModelResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/blogs/")
public class BlogController {
    @GetMapping
    public ResponseEntity<ModelResponse> retrieveAll(@RequestParam Map<String, String> params){
        ModelResponse res = new ModelResponse();
        res.setStatus(200);
        return ResponseEntity.ok(res);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ModelResponse> getById(@PathVariable(name = "id") int id) {
        ModelResponse res = new ModelResponse();
        res.setStatus(200);
        return ResponseEntity.ok(res);
    }
}
