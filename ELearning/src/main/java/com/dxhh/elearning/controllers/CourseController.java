package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.request.CourseUpdateRequest;
import com.dxhh.elearning.dto.request.ListRequest;
import com.dxhh.elearning.dto.request.NewCourseRequest;
import com.dxhh.elearning.dto.request.NewSectionRequest;
import com.dxhh.elearning.dto.response.CourseDetailsResponse;
import com.dxhh.elearning.dto.response.CourseInfoResponse;
import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.mappers.CourseMapper;
import com.dxhh.elearning.mappers.UserMapper;
import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.services.CourseService;
import com.dxhh.elearning.services.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/courses/", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {

    private final CourseService courseService;
    private final LectureService lectureService;
    private final CourseMapper courseMapper;
    private final UserMapper userMapper;


    @Autowired
    public CourseController(CourseService courseService, LectureService lectureService, CourseMapper courseMapper, UserMapper userMapper) {
        this.courseService = courseService;
        this.lectureService = lectureService;
        this.courseMapper = courseMapper;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<ModelResponse> retrieveAll(@RequestParam Map<String, String> params) {
        ModelResponse res = new ModelResponse();
        List<CourseInfoResponse> courses = new ArrayList<>();
        courseService.findCourses(params).stream().forEach(c -> {
            CourseInfoResponse info = courseMapper.toInfo(c);
            info.setCountRegistration(courseService.countRegistrationById(c.getId()));
            info.setUser(userMapper.toResponse(c.getCreator()));
            courses.add(info);

        });
        res.setData(courses);
        res.setStatus(200);
        return ResponseEntity.ok(res);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ModelResponse> getById(@PathVariable(name = "id") int id) {
        ModelResponse res = new ModelResponse();
        Course course = courseService.findById(id);
        CourseDetailsResponse response = courseMapper.toDetail(course);
//        response.setCriterias();
        res.setStatus(200);
        return ResponseEntity.ok(res);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ModelResponse> create(@ModelAttribute("course") NewCourseRequest course,
                                                BindingResult rs) {
        ModelResponse response = new ModelResponse();
        if (rs.hasErrors()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Validation errors");
            response.setData(rs.getAllErrors());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Course savedCourse = courseService.save(course);

        if (savedCourse != null) {
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("Course created successfully");
            response.setData(courseMapper.toDetail(savedCourse));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Failed to create course");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/after-create-course")
    public ResponseEntity createSection(@ModelAttribute ListRequest request, BindingResult rs) {
        ModelResponse response = new ModelResponse();
        if (rs.hasErrors()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Validation errors");
            response.setData(rs.getAllErrors());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        if (request != null) {
            request.getSections().forEach(s -> {

                s.getLectures().forEach(l -> lectureService.create(l));
            });
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("Course created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Failed to create section");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> update(@ModelAttribute CourseUpdateRequest courseRequest, BindingResult rs) {
        return null;
    }

    @DeleteMapping(path = "/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
