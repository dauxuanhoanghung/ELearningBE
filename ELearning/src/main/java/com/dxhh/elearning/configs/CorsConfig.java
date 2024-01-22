package com.dxhh.elearning.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {
    private static final String[] WHITELIST = {
            "http://localhost:3000"
    };

    private static final String[] METHODS = { "GET","POST","PATCH", "PUT", "DELETE", "OPTIONS", "HEAD" };

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(WHITELIST));
        configuration.setAllowedMethods(Arrays.asList(METHODS));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        // Không có dòng này mơ mà register được kênh của websocket | Enable sending credentials (e.g., cookies)
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }



    @Bean
    public CorsConfiguration corsConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(WHITELIST));
        configuration.setAllowedMethods(Arrays.asList(METHODS));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        return configuration;
    }

}

