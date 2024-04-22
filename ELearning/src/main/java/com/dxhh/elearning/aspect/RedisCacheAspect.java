package com.dxhh.elearning.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

@Aspect
@Component
public class RedisCacheAspect {
    private final CacheManager cacheManager;

    public RedisCacheAspect(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Around("@annotation(cacheable)")
    public Object cache(ProceedingJoinPoint joinPoint, Cacheable cacheable) throws Throwable {
        // Generating cache key
        String cacheKey = generateCacheKey(joinPoint, cacheable, joinPoint.getArgs());

        // Retrieve cache
        Object value = Objects.requireNonNull(cacheManager.getCache(Arrays.toString(cacheable.value()))).get(cacheKey, Object.class);
        if (value != null) {
            return value;
        }

        // Proceed with method invocation
        Object result = joinPoint.proceed();

        // Store result in cache
        CompletableFuture.runAsync(() -> {
            Objects.requireNonNull(cacheManager.getCache(Arrays.toString(cacheable.value()))).put(cacheKey, result);
        });

        return result;
    }

//    @Around("@annotation(cacheEvict)")
//    public Object cacheDelete(ProceedingJoinPoint joinPoint, CacheEvict cacheEvict) throws Throwable {
//        // Proceed with method invocation
//        Object result = joinPoint.proceed();
//
//        // Delete cache entries
//        for (String cacheName : cacheEvict.cacheNames()) {
//            Cache cache = Objects.requireNonNull(cacheManager.getCache(cacheName));
//            for (Object key : cache.getNativeCache().keys().stream().filter(k -> k.toString().startsWith(cacheName + "::")).collect(Collectors.toList())) {
//                cache.evict(key);
//            }
//        }
//
//        return result;
//    }

    private String generateCacheKey(ProceedingJoinPoint joinPoint, Cacheable cacheable, Object[] args) {
        StringBuilder sb = new StringBuilder();

        String cacheNames = Arrays.toString(cacheable.cacheNames());
        if (cacheNames.isEmpty()) {
            sb.append(joinPoint.getSignature().getDeclaringTypeName()).append("::");
            sb.append(joinPoint.getSignature().getName()).append("::");
        }
        else {
            sb.append(cacheNames).append("::");
        }
        
        // If key expression is provided, use it
        String keyExpression = cacheable.key();
        if (!keyExpression.isEmpty()) {
            sb.append(args[0]);
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

