package com.dxhh.elearning.aspect;

import com.dxhh.elearning.pojos.Transaction;
import com.dxhh.elearning.services.CourseService;
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

    public TransactionAspect(CourseService courseService) {
        this.courseService = courseService;
    }

    @AfterReturning(pointcut = "execution(* com.dxhh.elearning.services.impl.TransactionServiceImpl.create(..))", returning = "transaction")
    @Async
    public void updateCourseCountAfterTransactionCreate(Transaction transaction) {
        courseService.incrementCourseCount(transaction.getCourse().getId());
    }
}