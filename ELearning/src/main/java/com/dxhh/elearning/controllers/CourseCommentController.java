package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.pojos.CourseComment;
import com.dxhh.elearning.services.CourseCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/course-comments/", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseCommentController {
    private final CourseCommentService courseCommentService;

    @Autowired
    public CourseCommentController(CourseCommentService courseCommentService) {
        this.courseCommentService = courseCommentService;
    }

    // Create a new course comment
    @PostMapping
    public ResponseEntity<ModelResponse> createCourseComment(@RequestBody CourseComment courseComment) {
        CourseComment createdCourseComment = courseCommentService.create(courseComment);
        ModelResponse response = new ModelResponse();
        response.setStatus(201);
        response.setData(createdCourseComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<ModelResponse> getCommentByCourse(@PathVariable Integer courseId) {
        List<CourseComment> comments = courseCommentService.getByCourseId(courseId, 0);

        ModelResponse response = new ModelResponse();
        response.setStatus(200);
        response.setData(comments);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ModelResponse> retrieveAll(@RequestParam Map<String, String> params){
        ModelResponse res = new ModelResponse();
        res.setStatus(200);
        return ResponseEntity.ok(res);
    }

    // Update a course comment by ID
    @PutMapping("/{commentId}")
    public ResponseEntity<ModelResponse> updateCourseComment(
            @PathVariable Integer commentId,
            @RequestBody CourseComment updatedComment) {
        CourseComment updated = courseCommentService.update(commentId, updatedComment);
        ModelResponse response = new ModelResponse();
        if (updated != null) {
            response.setStatus(200);
            response.setData(updated);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(404);
            response.setMessage("Lecture comment not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Delete a course comment by ID
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ModelResponse> deleteCourseComment(@PathVariable Integer commentId) {
        boolean deleted = courseCommentService.deleteById(commentId);
        ModelResponse response = new ModelResponse();
        if (deleted) {
            response.setStatus(204);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } else {
            response.setStatus(404);
            response.setMessage("Lecture comment not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
