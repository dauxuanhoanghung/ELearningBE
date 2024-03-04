package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.request.NewTransactionRequest;
import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.pojos.Lecture;
import com.dxhh.elearning.pojos.Transaction;
import com.dxhh.elearning.services.CourseService;
import com.dxhh.elearning.services.LectureService;
import com.dxhh.elearning.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/registration/")
@CrossOrigin(originPatterns = "*")
public class RegistrationController {
    private final CourseService courseService;
    private final TransactionService transactionService;
    private final LectureService lectureService;

    public RegistrationController(CourseService courseService, TransactionService transactionService, LectureService lectureService) {
        this.courseService = courseService;
        this.transactionService = transactionService;
        this.lectureService = lectureService;
    }

    @PostMapping
    public ResponseEntity<ModelResponse> registerCourse(@RequestBody NewTransactionRequest request) {
        Course course = courseService.findById(request.getCourse().getId());
        if (course.getPrice() > 0 && request.getAmount().equals(BigDecimal.valueOf(0))) {
            Map map = new HashMap();
            map.put("nextUrl", "");
            ModelResponse res = new ModelResponse(HttpStatus.OK.value(), "Redirect", map);
            return ResponseEntity.ok(res);
        } else {
            Transaction transaction = transactionService.create(request);
            Lecture lecture = lectureService.getFirstLectureOfCourse(request.getCourse().getId());
            Map map = new HashMap();
            map.put("transaction", transaction);
            map.put("nextUrl", lecture.getId());
            ModelResponse res = new ModelResponse(HttpStatus.CREATED.value(), "Register success", map);
            return ResponseEntity.ok(res);
        }
    }

    @GetMapping("/{courseId}/get-current-user")
    public ResponseEntity<ModelResponse> getInit(@PathVariable Integer courseId) {
        Map map = new HashMap();
        Transaction transaction = transactionService.getByCurrentUserAndCourse(courseId);
        if (transaction != null) {
            Lecture lecture = lectureService.getFirstLectureOfCourse(courseId);
            map.put("nextUrl", lecture.getId());
        }
        map.put("transaction", transaction);
        ModelResponse res = new ModelResponse(HttpStatus.OK.value(), "Register success", map);
        return ResponseEntity.ok(res);
    }
}
