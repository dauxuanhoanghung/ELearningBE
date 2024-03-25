package com.dxhh.elearning.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CacheKeyGenerator {
    public static String generateForId(String prefix, Integer id) {
        return prefix + "::#" + id;
    }

    public static String generateForMap(String prefix, Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return prefix + "::";
        }

        // Sort the map keys
        Map<String, String> sortedParams = new TreeMap<>(params);

        String paramsString = sortedParams.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("::"));

        return prefix + "::" + paramsString;
    }

    public static String generate(String prefix, Object... params) {
        StringBuilder sb = new StringBuilder(prefix).append("::");

        for (Object param : params) {
            if (param instanceof Integer) {
                sb.append("#").append(param);
            } else if (param instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, String> map = (Map<String, String>) param;
                Map<String, String> sortedParams = new TreeMap<>(map);
                String paramsString = sortedParams.entrySet().stream()
                        .map(entry -> entry.getKey() + "=" + entry.getValue())
                        .collect(Collectors.joining("::"));
                sb.append(paramsString);
            } else {
                sb.append(StringUtils.defaultString(param.toString()));
            }
        }

        return sb.toString();
    }
}
