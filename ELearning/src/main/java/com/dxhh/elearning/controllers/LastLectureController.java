package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.utils.Routing;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(path = Routing.LAST_LECTURE, produces = MediaType.APPLICATION_JSON_VALUE)
public class LastLectureController {

    @GetMapping()
    public ResponseEntity<ModelResponse> getList() {
        ModelResponse response = new ModelResponse();
        response.setStatus(200);
        response.setMessage("Success");
        return ResponseEntity.ok(response);
    }

}
