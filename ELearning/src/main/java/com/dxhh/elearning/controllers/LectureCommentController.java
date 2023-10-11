package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.request.LectureCommentRequest;
import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.pojos.LectureComment;
import com.dxhh.elearning.services.LectureCommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping(value = "/api/lecture-comments/", produces = MediaType.APPLICATION_JSON_VALUE)
public class LectureCommentController {
    private final LectureCommentService lectureCommentService;

    public LectureCommentController(LectureCommentService lectureCommentService) {
        this.lectureCommentService = lectureCommentService;
    }

    @PostMapping
    public ResponseEntity<ModelResponse> create(@RequestBody LectureCommentRequest lectureComment) {
        LectureComment createdLectureComment = lectureCommentService.save(lectureComment);
        ModelResponse response = new ModelResponse();
        response.setStatus(201);
        response.setData(createdLectureComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/lecture/{lectureId}")
    public ResponseEntity<ModelResponse> getCommentByLecture(@PathVariable Integer lectureId,
                                                             @RequestParam Map<String, String> params) {
        int page = 0;
        if (params.containsKey("page"))
            page = Integer.valueOf(params.get("page"));
        List<LectureComment> comments = lectureCommentService.getByLectureId(lectureId, page);
        ModelResponse response = new ModelResponse();
        response.setStatus(200);
        response.setData(comments);
        return ResponseEntity.ok(response);
    }

    // Update a course comment by ID
    @PutMapping("/{commentId}")
    public ResponseEntity<ModelResponse> updateCourseComment(@PathVariable Integer commentId,
                                                             @RequestBody LectureComment updatedComment) {
        LectureComment updated = lectureCommentService.update(updatedComment);
        ModelResponse response = new ModelResponse();
        if (updated != null) {
            response.setStatus(200);
            response.setData(updated);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(404);
            response.setMessage("Course comment not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Delete a course comment by ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ModelResponse> deleteLectureComment(@PathVariable("id") Integer id) {
        boolean deleted = lectureCommentService.deleteById(id);

        ModelResponse response = new ModelResponse();
        if (deleted) {
            response.setStatus(204);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } else {
            response.setStatus(404);
            response.setMessage("Course comment not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
