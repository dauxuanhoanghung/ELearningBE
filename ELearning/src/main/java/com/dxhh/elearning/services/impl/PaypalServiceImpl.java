package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.dto.response.CompletedOrder;
import com.dxhh.elearning.dto.response.PaymentOrder;
import com.dxhh.elearning.repositories.TransactionRepository;
import com.dxhh.elearning.services.CourseService;
import com.dxhh.elearning.services.PaypalService;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PaypalServiceImpl implements PaypalService {

    private final PayPalHttpClient payPalHttpClient;
    private final CourseService courseService;
    private final TransactionRepository transactionRepository;
    private final Environment env;

    public PaypalServiceImpl(PayPalHttpClient payPalHttpClient,
                             CourseService courseService,
                             TransactionRepository transactionRepository,
                             Environment env) {
        this.payPalHttpClient = payPalHttpClient;
        this.courseService = courseService;
        this.transactionRepository = transactionRepository;
        this.env = env;
    }

    @Override
    public PaymentOrder createPayment(BigDecimal fee) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");
        AmountWithBreakdown amountBreakdown = new AmountWithBreakdown().currencyCode("USD").value(fee.toString());
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest().amountWithBreakdown(amountBreakdown);
        orderRequest.purchaseUnits(List.of(purchaseUnitRequest));
        ApplicationContext applicationContext = new ApplicationContext()
                .returnUrl(env.getProperty("clientUrl") + "/paypal/capture")
                .cancelUrl(env.getProperty("clientUrl") + "/paypal/cancel");
        orderRequest.applicationContext(applicationContext);
        OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);

        try {
            HttpResponse<Order> orderHttpResponse = payPalHttpClient.execute(ordersCreateRequest);
            Order order = orderHttpResponse.result();

            String redirectUrl = order.links().stream()
                    .filter(link -> "approve".equals(link.rel()))
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new)
                    .href();

            return new PaymentOrder("success", order.id(), redirectUrl);
        } catch (IOException e) {
            return new PaymentOrder("Error");
        }
    }

    public CompletedOrder completePayment(String token) {
        OrdersCaptureRequest ordersCaptureRequest = new OrdersCaptureRequest(token);
        try {
            HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersCaptureRequest);
            if (httpResponse.result().status() != null) {
                return new CompletedOrder("success", token);
            }
        } catch (IOException e) {
        }
        return new CompletedOrder("error");
    }
}
