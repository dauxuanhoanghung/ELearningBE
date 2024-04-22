package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.dto.request.NewTransactionRequest;
import com.dxhh.elearning.dto.response.CompletedOrder;
import com.dxhh.elearning.dto.response.PaymentOrder;
import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.pojos.Transaction;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.repositories.TransactionRepository;
import com.dxhh.elearning.repositories.UserRepository;
import com.dxhh.elearning.services.CourseService;
import com.dxhh.elearning.services.CurrentUserService;
import com.dxhh.elearning.services.PaypalService;
import com.dxhh.elearning.services.TransactionService;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PaypalServiceImpl extends CurrentUserService implements PaypalService {

    private final PayPalHttpClient payPalHttpClient;
    private final CourseService courseService;
    private final TransactionService transactionService;
    private final Environment env;


    public PaypalServiceImpl(PayPalHttpClient payPalHttpClient,
                             CourseService courseService,
                             UserRepository userRepository,
                             TransactionService transactionService,
                             Environment env) {
        super(userRepository);
        this.payPalHttpClient = payPalHttpClient;
        this.courseService = courseService;
        this.transactionService = transactionService;
        this.env = env;
    }

    @Override
    public PaymentOrder createPayment(NewTransactionRequest request) {
        Course course = courseService.findById(request.getCourse().getId());
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");
        AmountWithBreakdown amountBreakdown = new AmountWithBreakdown()
                .currencyCode("USD")
                .value(String.valueOf(course.getPrice()));
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                .amountWithBreakdown(amountBreakdown)
                .referenceId(course.getId().toString()) //pass course id in body request
                ;
        orderRequest.purchaseUnits(List.of(purchaseUnitRequest));
        ApplicationContext applicationContext = new ApplicationContext()
                .returnUrl(env.getProperty("clientUrl") + "/payment/paypal/capture")
                .cancelUrl(env.getProperty("clientUrl") + "/paypal/paypal/cancel");
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
            Order order = httpResponse.result();
            if (order.status() != null) {
                User user = this.getCurrentUser();

                PurchaseUnit purchaseUnit = order.purchaseUnits().get(0);
                Capture capture = purchaseUnit.payments().captures().get(0);

                Integer courseId = Integer.valueOf(purchaseUnit.referenceId());
                BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(capture.amount().value()));

                NewTransactionRequest transactionRequest = NewTransactionRequest.builder()
                        .amount(amount)
                        .username(user.getUsername())
                        .course(new Course(courseId))
                        .build();
                transactionService.create(transactionRequest);
                return new CompletedOrder("success", token);
            }
        } catch (IOException ignored) {
        }
        return new CompletedOrder("error");
    }
}
