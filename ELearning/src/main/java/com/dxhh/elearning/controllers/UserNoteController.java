package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.pojos.Lecture;
import com.dxhh.elearning.pojos.UserNote;
import com.dxhh.elearning.services.LectureService;
import com.dxhh.elearning.services.UserNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-notes")
public class UserNoteController {
    private final UserNoteService userNoteService;
    private final LectureService lectureService;

    @Autowired
    public UserNoteController(UserNoteService userNoteService, LectureService lectureService) {
        this.userNoteService = userNoteService;
        this.lectureService = lectureService;
    }

    @PostMapping
    public ResponseEntity<ModelResponse> createUserNote(@RequestBody UserNote userNote) {
        UserNote createdUserNote = userNoteService.create(userNote);
        ModelResponse response = new ModelResponse(HttpStatus.CREATED.value(), "User note created successfully", createdUserNote);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ModelResponse> deleteUserNote(@PathVariable Integer id) {
        userNoteService.deleteById(id);
        ModelResponse response = new ModelResponse(HttpStatus.NO_CONTENT.value(), "User note deleted successfully", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @GetMapping("/{lectureId}/get-note")
    public ResponseEntity<ModelResponse> getUserNotesByUserAndLecture(@PathVariable Integer lectureId) {
        Lecture lecture = lectureService.getById(lectureId);
        if (lecture != null) {
            List<UserNote> userNotes = userNoteService.findByUserAndLecture(lecture);
            ModelResponse response = new ModelResponse(HttpStatus.OK.value(), "User notes retrieved successfully", userNotes);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            ModelResponse response = new ModelResponse(HttpStatus.NOT_FOUND.value(), "User or Lecture not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
