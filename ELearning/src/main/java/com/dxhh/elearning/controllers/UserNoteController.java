package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.mappers.UserNoteMapper;
import com.dxhh.elearning.pojos.Lecture;
import com.dxhh.elearning.pojos.UserNote;
import com.dxhh.elearning.services.LectureService;
import com.dxhh.elearning.services.UserNoteService;
import com.dxhh.elearning.utils.Routing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Routing.USER_NOTES, produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin(originPatterns = "*")
public class UserNoteController {
    private final UserNoteService userNoteService;
    private final LectureService lectureService;
    private final UserNoteMapper userNoteMapper;

    @Autowired
    public UserNoteController(UserNoteService userNoteService, LectureService lectureService, UserNoteMapper userNoteMapper) {
        this.userNoteService = userNoteService;
        this.lectureService = lectureService;
        this.userNoteMapper = userNoteMapper;
    }

    @PostMapping
    public ResponseEntity<ModelResponse> createUserNote(@RequestBody UserNote userNote) {
        UserNote createdUserNote = userNoteService.create(userNote);
        ModelResponse response = new ModelResponse(HttpStatus.CREATED.value(),
                "User note created successfully", userNoteMapper.toResponse(createdUserNote));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ModelResponse> deleteUserNote(@PathVariable Integer id) {
        userNoteService.deleteById(id);
        ModelResponse response = new ModelResponse(HttpStatus.NO_CONTENT.value(),
                "User note deleted successfully", null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{lectureId}/get-note")
    public ResponseEntity<ModelResponse> getUserNotesByUserAndLecture(@PathVariable Integer lectureId) {
        Lecture lecture = lectureService.getById(lectureId);
        if (lecture != null) {
            List<UserNote> userNotes = userNoteService.findByUserAndLecture(lecture);
            List userNoteResponse = userNotes.stream().map(u -> userNoteMapper.toResponse(u)).toList();
            ModelResponse response = new ModelResponse(HttpStatus.OK.value(),
                    "User notes retrieved successfully", userNoteResponse);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            ModelResponse response = new ModelResponse(HttpStatus.NOT_FOUND.value(), "User or Lecture not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
