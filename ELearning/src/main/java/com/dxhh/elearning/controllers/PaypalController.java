package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.response.CompletedOrder;
import com.dxhh.elearning.dto.response.PaymentOrder;
import com.dxhh.elearning.services.PaypalService;
import com.dxhh.elearning.utils.Routing;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(value = Routing.PAYPAL)
public class PaypalController {
    private final PaypalService paypalService;

    public PaypalController(PaypalService paypalService) {
        this.paypalService = paypalService;
    }

    @PostMapping(value = "/init")
    public PaymentOrder createPayment(@RequestParam("sum") BigDecimal sum) {
        return paypalService.createPayment(sum);
    }

    @PostMapping(value = "/capture")
    public CompletedOrder completePayment(@RequestParam("token") String token) {
        return paypalService.completePayment(token);
    }
}
