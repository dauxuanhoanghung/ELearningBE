package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.request.NewTransactionRequest;
import com.dxhh.elearning.dto.response.CourseDetailsResponse;
import com.dxhh.elearning.dto.response.CourseInfoResponse;
import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.mappers.CourseMapper;
import com.dxhh.elearning.mappers.UserMapper;
import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.pojos.Lecture;
import com.dxhh.elearning.pojos.Transaction;
import com.dxhh.elearning.services.CourseService;
import com.dxhh.elearning.services.LectureService;
import com.dxhh.elearning.services.TransactionService;
import com.dxhh.elearning.utils.Routing;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = Routing.REGISTRATION, produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(originPatterns = "*")
public class RegistrationController {
    private final CourseService courseService;
    private final TransactionService transactionService;
    private final LectureService lectureService;
    private final UserMapper userMapper;
    private final CourseMapper courseMapper;

    public RegistrationController(CourseService courseService,
                                  TransactionService transactionService,
                                  LectureService lectureService,
                                  UserMapper userMapper,
                                  CourseMapper courseMapper) {
        this.courseService = courseService;
        this.transactionService = transactionService;
        this.lectureService = lectureService;
        this.userMapper = userMapper;
        this.courseMapper = courseMapper;
    }

    @PostMapping
    public ResponseEntity<ModelResponse> register(@RequestBody NewTransactionRequest request) {
        Course course = courseService.findById(request.getCourse().getId());
        Map<String, Object> map = new HashMap<>();
        // Redirect to payment page if course is not free, if course.price > 0 => has payed
        if (course.getPrice() > 0 && request.getAmount().equals(BigDecimal.valueOf(0))) {
            map.put("nextUrl", "");
            ModelResponse res = new ModelResponse(HttpStatus.OK.value(), "Redirect", map);
            return ResponseEntity.ok(res);
        } else {
            // Register course if course is free
            Transaction transaction = transactionService.create(request);
            Lecture lecture = lectureService.getFirstLectureOfCourse(request.getCourse().getId());
            map.put("transaction", transaction);
            map.put("nextUrl", lecture.getId());
            ModelResponse res = new ModelResponse(HttpStatus.CREATED.value(), "Register success", map);
            return ResponseEntity.ok(res);
        }
    }

    @GetMapping("/{courseId}/get-current-user")
    public ResponseEntity<ModelResponse> getInit(@PathVariable Integer courseId) {
        Map<String, Object> map = new HashMap<>();
        Transaction transaction = transactionService.getByCurrentUserAndCourse(courseId);
        if (transaction != null) {
            Lecture lecture = lectureService.getFirstLectureOfCourse(courseId);
            map.put("nextUrl", lecture.getId());
        }
        map.put("transaction", transaction);
        ModelResponse res = ModelResponse.builder()
                .message("Get course has been registered or not success - course: " + courseId)
                .status(HttpStatus.OK.value())
                .data(map)
                .build();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/get-registered-courses")
    public ResponseEntity<ModelResponse> getRegisteredCourses(Map<String, String> params) {
        List<Course> transactions = courseService.findRegisteredCourses(params);

        ModelResponse res = ModelResponse.builder()
                .data(transactions.stream().map(transaction -> {
                    CourseDetailsResponse courseInfoResponse = courseMapper.toDetail(transaction);
                    courseInfoResponse.setUser(userMapper.toResponse(transaction.getCreator(), false));
                    return courseInfoResponse;
                }).toList())
                .status(HttpStatus.OK.value())
                .message("Get registered courses success")
                .build();
        return ResponseEntity.ok(res);
    }

    @GetMapping
    public ResponseEntity<ModelResponse> getList(@RequestParam Map<String, String> params) {
        List<Course> courses = courseService.findRegisteredCourses(params);
        ModelResponse res = ModelResponse.builder()
                .data(transactionService.getAll(params))
                .status(HttpStatus.OK.value())
                .message("Get all registrations success")
                .build();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/count")
    public ResponseEntity<ModelResponse> count(@RequestParam Map<String, String> params) {
        ModelResponse res = ModelResponse.builder()
                .data(transactionService.count(params))
                .status(HttpStatus.OK.value())
                .message("Count registrations success")
                .build();
        return ResponseEntity.ok(res);
    }
}
