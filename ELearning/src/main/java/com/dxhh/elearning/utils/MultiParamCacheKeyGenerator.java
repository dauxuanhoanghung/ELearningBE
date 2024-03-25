package com.dxhh.elearning.utils;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class MultiParamCacheKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder cacheKey = new StringBuilder();
        cacheKey.append(method.getName()); // Include method name

        for (Object param : params) {
            cacheKey.append("::");
            if (param != null) {
                cacheKey.append(param.toString());
            } else {
                cacheKey.append("null");
            }
        }

        return cacheKey.toString();
    }

    public static void main(String[] args) {

    }
}
