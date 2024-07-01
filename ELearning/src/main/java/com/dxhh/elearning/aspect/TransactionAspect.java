package com.dxhh.elearning.aspect;

import com.dxhh.elearning.pojos.Transaction;
import com.dxhh.elearning.services.CourseService;
import com.dxhh.elearning.services.UserService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
@Transactional
public class TransactionAspect {
    private final CourseService courseService;

    private final UserService userService;

    public TransactionAspect(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @AfterReturning(pointcut = "execution(* com.dxhh.elearning.services.impl.TransactionServiceImpl.create(..))", returning = "transaction")
    @Async
    public void updateCourseCountAfterTransactionCreate(Transaction transaction) {
        courseService.incrementCourseCount(transaction.getCourse().getId());
        Integer userId = transaction.getCourse().getCreator().getId();
        Double credit = userService.getCreditByUserId(userId) + Double.parseDouble(transaction.getAmount().toString());
        userService.updateCreditByUserId(userId, credit);
    }
}