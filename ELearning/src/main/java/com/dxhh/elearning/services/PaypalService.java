package com.dxhh.elearning.services;

import com.dxhh.elearning.dto.response.CompletedOrder;
import com.dxhh.elearning.dto.response.PaymentOrder;

import java.math.BigDecimal;

public interface PaypalService {
    PaymentOrder createPayment(BigDecimal fee);
    CompletedOrder completePayment(String token);
}
