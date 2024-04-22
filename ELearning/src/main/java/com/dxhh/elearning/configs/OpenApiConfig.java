package com.dxhh.elearning.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Elearning API",
                version = "1.0",
                description = "Elearning API"
        )
)
public class OpenApiConfig {
    // Your additional OpenAPI configurations, if needed
}