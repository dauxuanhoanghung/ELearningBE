package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.dto.response.SectionResponse;
import com.dxhh.elearning.services.SectionService;
import com.dxhh.elearning.utils.Routing;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
