package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.request.CourseUpdateRequest;
import com.dxhh.elearning.dto.request.ListRequest;
import com.dxhh.elearning.dto.request.NewCourseRequest;
import com.dxhh.elearning.dto.response.*;
import com.dxhh.elearning.mappers.CourseMapper;
import com.dxhh.elearning.mappers.LectureMapper;
import com.dxhh.elearning.mappers.UserMapper;
import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.pojos.Section;
import com.dxhh.elearning.services.CourseCriteriaService;
import com.dxhh.elearning.services.CourseService;
import com.dxhh.elearning.services.LectureService;
import com.dxhh.elearning.services.SectionService;
import com.dxhh.elearning.utils.Routing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(value = Routing.COURSES, produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {
    private final CourseService courseService;
    private final LectureService lectureService;
    private final SectionService sectionService;
    private final CourseCriteriaService courseCriteriaService;
    private final CourseMapper courseMapper;
    private final UserMapper userMapper;
    private final LectureMapper lectureMapper;
    private final Environment env;

    public CourseController(CourseService courseService,
                            LectureService lectureService,
                            SectionService sectionService,
                            CourseCriteriaService courseCriteriaService,
                            CourseMapper courseMapper,
                            UserMapper userMapper,
                            LectureMapper lectureMapper,
                            Environment env) {
        this.courseService = courseService;
        this.lectureService = lectureService;
        this.sectionService = sectionService;
        this.courseCriteriaService = courseCriteriaService;
        this.courseMapper = courseMapper;
        this.userMapper = userMapper;
        this.lectureMapper = lectureMapper;
        this.env = env;
    }

    private ModelResponse getModelListCoursesResponse(Map<String, String> params) {
        ModelResponse res = new ModelResponse();
        List<CourseInfoResponse> courses = new ArrayList<>();
        courseService.findAll(params).forEach(c -> {
            CourseInfoResponse info = courseMapper.toInfo(c);
            info.setUser(userMapper.toResponse(c.getCreator(), false));
            courses.add(info);
        });
        res.setData(courses);
        res.setStatus(200);
        return res;
    }

    @GetMapping
    public ResponseEntity<ModelResponse> retrieveAll(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok(getModelListCoursesResponse(params));
    }

    @GetMapping("/get-total-page")
    public ResponseEntity<ModelResponse> getTotalPage(@RequestParam Map<String, String> params) {
        int size = env.getProperty("SIZE", Integer.class, 8);
        if (params.containsKey("pageSize")) {
            size = Integer.parseInt(params.get("pageSize"));
        }
        Integer totalCount = courseService.count(params);
        Long totalPage = (long) Math.ceil((double) totalCount / size);
        ModelResponse res = new ModelResponse(HttpStatus.OK.value(), "Total course page", totalPage);
        return ResponseEntity.ok(res);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ModelResponse> getById(@PathVariable(name = "id") int id) {
        ModelResponse res = new ModelResponse();
        Course course = courseService.findById(id);
        CourseDetailsResponse response = null;
        if (course != null) {
            response = courseMapper.toDetail(course);
            response.setUser(userMapper.toResponse(course.getCreator()));
        }
        res.setStatus(200);
        res.setData(response);
        return ResponseEntity.ok(res);
    }

    @GetMapping(path = "/my-business")
    public ResponseEntity<ModelResponse> getByCreator(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok(getModelListCoursesResponse(params));
    }

    @GetMapping(path = "/my-learning")
    public ResponseEntity<ModelResponse> getLearningCourse(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok(getModelListCoursesResponse(params));
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ModelResponse> create(@ModelAttribute NewCourseRequest course,
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

    @PostMapping(value = "/after-create-course", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createSection(@RequestBody ListRequest sections, BindingResult rs) {
        ModelResponse response = new ModelResponse();
        if (rs.hasErrors()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Validation errors");
            response.setData(rs.getAllErrors());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        if (!sections.getSections().isEmpty()) {
            List res = new ArrayList<>();
            sections.getSections().forEach(s -> {
                res.add(sectionService.createSection(s));
            });
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("Course created successfully");
            response.setData(res);
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
        Course updated = courseService.update(courseRequest);
        return ResponseEntity.ok(ModelResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Course updated successfully id=" + courseRequest.getId())
                .data(courseMapper.toDetail(updated))
                .build());
    }

    @DeleteMapping(path = "/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/get-criteria")
    public ResponseEntity<ModelResponse> getCriteriaByCourseId(@PathVariable(name = "id") int id) {
        ModelResponse res = new ModelResponse();
        res.setData(courseCriteriaService.getByCourseId(id));
        res.setStatus(200);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}/get-count-lectures")
    public ResponseEntity<ModelResponse> getCountLectures(@PathVariable(name = "id") int id) {
        ModelResponse res = new ModelResponse(200,
                "Get count successful", courseService.countLecturesByCourseId(id));
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}/get-count-registration")
    public ResponseEntity<ModelResponse> getCountRegistrations(@PathVariable(name = "id") int id) {
        ModelResponse res = new ModelResponse(200,
                "Get count successful", courseService.countRegistrationByCourseId(id));
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}/get-section")
    public ResponseEntity<ModelResponse> getSectionByCourseId(@PathVariable(name = "id") int id) {
        ModelResponse res = new ModelResponse();
        res.setData(sectionService.getByCourse_Id(id));
        res.setStatus(200);
        return ResponseEntity.ok(res);
    }


    @GetMapping("/{id}/get-section-lectures")
    public ResponseEntity<ModelResponse> getSectionAndItsLectureByCourseId(@PathVariable(name = "id") int id) {
        List<Section> sections = sectionService.getByCourse_Id(id);
        List<SectionResponse> result = sections.stream().map(s -> {
            List<LectureResponse> lectures = lectureService.getBySectionId(s.getId())
                    .stream().map(lectureMapper::toResponse).toList();
            return new SectionResponse(s.getId(), s.getName(), s.getOrderIndex(), lectures);
        }).toList();

        ModelResponse res = new ModelResponse(HttpStatus.OK.value(), "Get success", result);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/count")
    public ResponseEntity<ModelResponse> count(@RequestParam Map<String, String> params) {
        ModelResponse res = new ModelResponse(200, "Count course", courseService.count(params));
        return ResponseEntity.ok(res);
    }
}
