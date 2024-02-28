package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.request.NewLectureRequest;
import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.mappers.LectureMapper;
import com.dxhh.elearning.pojos.Lecture;
import com.dxhh.elearning.services.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping(value = "/api/lectures/", produces = MediaType.APPLICATION_JSON_VALUE)
public class LectureController {

    private final LectureService lectureService;
    private final LectureMapper lectureMapper;

    @Autowired
    public LectureController(LectureService lectureService, LectureMapper lectureMapper) {
        this.lectureService = lectureService;
        this.lectureMapper = lectureMapper;
    }

    @GetMapping
    public ResponseEntity<ModelResponse> retrieveAll(@RequestParam Map<String, String> params){
        ModelResponse res = new ModelResponse();
        List<Lecture> lectures = lectureService.getByCourseId(Integer.valueOf(params.get("courseId")));
        res.setStatus(200);
        res.setData(lectures);
        return ResponseEntity.ok(res);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ModelResponse> getById(@PathVariable(name = "id") int id) {
        Lecture lecture = lectureService.getById(id);
        ModelResponse res = new ModelResponse();
        res.setStatus(200);
        res.setData(lectureMapper.toResponse(lecture));
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<ModelResponse> createLecture(@ModelAttribute NewLectureRequest lectureRequest, BindingResult rs) {
        Lecture createdLecture = lectureService.create(lectureRequest);
        ModelResponse res = new ModelResponse();
        res.setStatus(201); // 201 Created
        res.setData(createdLecture);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ModelResponse> updateLecture(
            @PathVariable(name = "id") int id,
            @RequestBody Lecture updatedLecture) {
        Lecture updated = lectureService.update(updatedLecture);
        if (updated != null) {
            ModelResponse res = new ModelResponse();
            res.setStatus(200);
            res.setData(updated);
            return ResponseEntity.ok(res);
        } else {
            ModelResponse res = new ModelResponse();
            res.setStatus(404);
            res.setMessage("Lecture not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

    // Delete a lecture by ID
    @DeleteMapping(path = "/{id}/delete")
    public ResponseEntity<ModelResponse> deleteLecture(@PathVariable(name = "id") int id) {
        boolean deleted = lectureService.deleteById(id);
        ModelResponse res = new ModelResponse();
        if (deleted) {
            res.setStatus(204);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(res);
        } else {
            res.setStatus(404);
            res.setMessage("Lecture not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }
}
