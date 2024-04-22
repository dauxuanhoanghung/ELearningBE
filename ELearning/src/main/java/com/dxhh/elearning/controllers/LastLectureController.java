package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.response.LastLectureResponse;
import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.mappers.LastLectureMapper;
import com.dxhh.elearning.pojos.LastLecture;
import com.dxhh.elearning.services.LastLectureService;
import com.dxhh.elearning.utils.Routing;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(path = Routing.LAST_LECTURE, produces = MediaType.APPLICATION_JSON_VALUE)
public class LastLectureController {

    private final LastLectureService service;
    private final LastLectureMapper mapper;

    public LastLectureController(LastLectureService service,
                                 LastLectureMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<ModelResponse> getList(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "4") int size) {
        List<LastLecture> list = service.findListByCurrentUser(page, size);
        List<LastLectureResponse> responses = list.stream().map(mapper::toResponse).toList();
        ModelResponse response = ModelResponse.builder()
                .status(200)
                .message("Success get list last lecture")
                .data(responses)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<ModelResponse> getOne(@PathVariable Integer courseId) {
        LastLecture lastLecture = service.findByCourseId(courseId);
        LastLectureResponse result = null;
        if (lastLecture != null) {
            result = mapper.toResponse(lastLecture);
        }
        ModelResponse response = ModelResponse.builder()
                .status(200)
                .data(result)
                .message("Get last lecture success with courseId: " + courseId)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<ModelResponse> create(@RequestBody LastLecture lastLecture) {
        LastLecture result = service.save(lastLecture);
        ModelResponse response = ModelResponse.builder()
                .status(200)
                .build();

        if (result != null) {
            response.setData(mapper.toResponse(result));
            response.setMessage("Success create last lecture");
        }
        else {
            response.setMessage("User hasn't have permission to create last lecture");
        }
        return ResponseEntity.ok(response);
    }
}
