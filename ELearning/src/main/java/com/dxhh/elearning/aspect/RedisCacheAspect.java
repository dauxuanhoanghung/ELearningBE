package com.dxhh.elearning.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

@Aspect
@Component
public class RedisCacheAspect {

    @Autowired
    private CacheManager cacheManager;

    @Around("@annotation(cacheable)")
    public Object cache(ProceedingJoinPoint joinPoint, Cacheable cacheable) throws Throwable {
        // Generating cache key
        String cacheKey = generateCacheKey(joinPoint, cacheable.key(), joinPoint.getArgs());

        // Retrieve cache
        Object value = Objects.requireNonNull(cacheManager.getCache(Arrays.toString(cacheable.value()))).get(cacheKey, Object.class);
        if (value != null) {
            return value;
        }

        // Proceed with method invocation
        Object result = joinPoint.proceed();

        // Store result in cache
        Objects.requireNonNull(cacheManager.getCache(Arrays.toString(cacheable.value()))).put(cacheKey, result);

        return result;
    }

    private String generateCacheKey(ProceedingJoinPoint joinPoint, String keyExpression, Object[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append(joinPoint.getSignature().getDeclaringTypeName()).append("::");
        sb.append(joinPoint.getSignature().getName()).append("::");

        // If key expression is provided, use it
        if (!keyExpression.isEmpty()) {
            sb.append(keyExpression);
        } else {
            for (Object arg : args) {
                if (arg instanceof Map) {
                    @SuppressWarnings("unchecked")
                    Map<String, String> map = (Map<String, String>) arg;
                    // Sort the map keys
                    Map<String, String> sortedParams = new TreeMap<>(map);
                    sb.append(sortedParams);
                } else {
                    sb.append(arg.toString());
                }
            }
        }

        return sb.toString();
    }
}

