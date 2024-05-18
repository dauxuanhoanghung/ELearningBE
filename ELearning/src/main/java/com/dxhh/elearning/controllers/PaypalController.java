package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.request.NewTransactionRequest;
import com.dxhh.elearning.dto.response.CompletedOrder;
import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.pojos.Lecture;
import com.dxhh.elearning.services.LectureService;
import com.dxhh.elearning.services.PaypalService;
import com.dxhh.elearning.utils.Routing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(value = Routing.PAYPAL)
public class PaypalController {
    private final PaypalService paypalService;
    private final LectureService lectureService;

    public PaypalController(PaypalService paypalService,
                            LectureService lectureService) {
        this.paypalService = paypalService;
        this.lectureService = lectureService;
    }

    @PostMapping(value = "/init")
    public ResponseEntity<ModelResponse> createPayment(@RequestBody NewTransactionRequest request) {
        try {
            paypalService.createPayment(request);

            return ResponseEntity.ok(ModelResponse.builder()
                    .message("Payment created")
                    .data(paypalService.createPayment(request))
                    .status(200)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ModelResponse.builder()
                    .message(e.getMessage())
                    .status(400)
                    .build());
        }
    }

    @PostMapping(value = "/capture")
    public ResponseEntity<ModelResponse> completePayment(@RequestBody Request request) {
        try {
            CompletedOrder order = paypalService.completePayment(request.getToken());
            Lecture lecture = lectureService.getFirstLectureOfCourse(order.getCourseId());
            Map<String, Object> res = new HashMap<>();
            res.put("lecture", lecture);
            res.put("courseId", order.getCourseId());
            return ResponseEntity.ok(ModelResponse.builder()
                    .message("Payment completed")
                    .data(res)
                    .status(200)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ModelResponse.builder()
                    .message(e.getMessage())
                    .status(400)
                    .build());
        }
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Request {
        private String token;
    }
}
