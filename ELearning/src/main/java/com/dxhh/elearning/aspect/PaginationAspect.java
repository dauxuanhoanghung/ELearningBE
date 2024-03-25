package com.dxhh.elearning.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
public class PaginationAspect {

    @Pointcut("execution(* com.dxhh.elearning..*.*(.., java.util.Map<String,String>, ..))")
    public void paginationPointcut() {}

    @Before("paginationPointcut()")
    public void handlePagination(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();

        for (Object argument : arguments) {
            if (argument instanceof Map<?,?>) {
                Map<String, String> params = (Map<String, String>) argument;
                if (!params.containsKey("page")) {
                    params.put("page", "0"); // Set the default page value
                }
            }
        }
    }
}
