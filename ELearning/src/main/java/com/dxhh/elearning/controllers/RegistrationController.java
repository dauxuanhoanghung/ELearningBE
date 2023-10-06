package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.request.NewTransactionRequest;
import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.pojos.Lecture;
import com.dxhh.elearning.pojos.Transaction;
import com.dxhh.elearning.services.LectureService;
import com.dxhh.elearning.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/registration/")
@CrossOrigin
public class RegistrationController {

    private final TransactionService transactionService;
    private final LectureService lectureService;

    public RegistrationController(TransactionService transactionService, LectureService lectureService) {
        this.transactionService = transactionService;
        this.lectureService = lectureService;
    }

    @PostMapping
    public ResponseEntity<ModelResponse> registerCourse(@RequestBody NewTransactionRequest request) {
        Transaction transaction = transactionService.create(request);
        Lecture lecture = lectureService.getFirstLectureOfCourse(request.getCourse().getId());
        Map map = new HashMap();
        map.put("transaction", transaction);
        map.put("nextUrl",lecture.getId());
        ModelResponse res = new ModelResponse(HttpStatus.OK.value(), "Register success", map);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{courseId}/get-current-user")
    public ResponseEntity<ModelResponse> getInit(@PathVariable Integer courseId) {
        Transaction transaction = transactionService.getByCurrentUserAndCourse(courseId);
        Lecture lecture = lectureService.getFirstLectureOfCourse(courseId);
        Map map = new HashMap();
        map.put("transaction", transaction);
        map.put("nextUrl", lecture.getId());
        ModelResponse res = new ModelResponse(HttpStatus.OK.value(), "Register success", map);
        return ResponseEntity.ok(res);
    }
}
