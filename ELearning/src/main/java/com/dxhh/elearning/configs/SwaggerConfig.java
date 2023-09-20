//package com.dxhh.elearning.configs;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
//
//import java.util.Collections;
//
//@Configuration
//@EnableSwagger2WebMvc
////@Import(SpringDataRestConfiguration.class)
//public class SwaggerConfig {
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.dxhh.elearning"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(apiInfo());
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfo("HElearning", "REST APIs For HElearning",
//                "API v1",
//                "Terms of service",
//                new Contact("Dau Xuan Hoang Hung",
//                        "https://github.com/dauxuanhoanghung",
//                        "dxhh2510@gmail.com"),
//                "License of API",
//                "API license URL",
//                Collections.emptyList());
//    }
//}