package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.request.NewTransactionRequest;
import com.dxhh.elearning.dto.response.CompletedOrder;
import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.services.PaypalService;
import com.dxhh.elearning.utils.Routing;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(value = Routing.PAYPAL)
public class PaypalController {
    private final PaypalService paypalService;

    public PaypalController(PaypalService paypalService) {
        this.paypalService = paypalService;
    }

    @PostMapping(value = "/init")
    public ResponseEntity<ModelResponse> createPayment(@RequestBody NewTransactionRequest request) {
        try {
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
    public CompletedOrder completePayment(@RequestParam("token") String token) {
        return paypalService.completePayment(token);
    }
}
