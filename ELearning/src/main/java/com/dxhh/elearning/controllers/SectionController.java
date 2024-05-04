package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.request.ListRequest;
import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.dto.response.SectionResponse;
import com.dxhh.elearning.services.SectionService;
import com.dxhh.elearning.utils.Routing;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(value = Routing.SECTIONS, produces = MediaType.APPLICATION_JSON_VALUE)
public class SectionController {
    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<ModelResponse> getSectionsAndLecturesByCourseId(@PathVariable Integer id) {
        List<SectionResponse> sections = sectionService.getSectionsAndLecturesByCourseId(id);
        ModelResponse response = ModelResponse.builder()
                .message("Sections and lectures fetched successfully by course id: " + id)
                .status(200)
                .data(sections)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/create-batch", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createSection(@RequestBody ListRequest sections, BindingResult rs) {
        if (rs.hasErrors()) {
            return ResponseEntity.ok(ModelResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("Validation errors")
                    .data(rs.getAllErrors())
                    .build());
        }
        if (!sections.getSections().isEmpty()) {
            List res = new ArrayList<>();
            sections.getSections().forEach(s -> {
                res.add(sectionService.createSection(s));
            });
            ModelResponse response = new ModelResponse();
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("Course created successfully");
            response.setData(res);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return ResponseEntity.ok(ModelResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Failed to create section")
                .data(null)
                .build());

    }
}
