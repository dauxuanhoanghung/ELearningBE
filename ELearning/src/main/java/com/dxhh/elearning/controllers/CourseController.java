package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.request.NewCourseRequest;
import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/courses/", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
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

    @PostMapping
    public ResponseEntity<ModelResponse> create(@ModelAttribute NewCourseRequest courseRequest, BindingResult rs) {
        ModelResponse response = new ModelResponse();
        if (rs.hasErrors()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Validation errors");
            response.setData(rs.getAllErrors());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Course savedCourse = courseService.save(courseRequest);

        if (savedCourse != null) {
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("Course created successfully");
            response.setData(savedCourse);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Failed to create course");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
