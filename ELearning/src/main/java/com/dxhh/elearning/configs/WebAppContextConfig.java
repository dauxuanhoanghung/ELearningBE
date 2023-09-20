package com.dxhh.elearning.configs;

import com.dxhh.elearning.formatters.BlogFormatter;
import com.dxhh.elearning.formatters.CourseFormatter;
import com.dxhh.elearning.formatters.LectureFormatter;
import com.dxhh.elearning.formatters.SectionFormatter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "com.dxhh.elearning"
})
public class WebAppContextConfig implements WebMvcConfigurer {
    @Bean(name = "messageSource")
    public MessageSource getMessageResource() {
        ResourceBundleMessageSource messageResource = new ResourceBundleMessageSource();

        // Đọc vào file i18n/messages_xxx.properties
        // Ví dụ: i18n/messages_en.properties
        messageResource.setBasenames("messages/messages");
        messageResource.setDefaultEncoding("UTF-8");
        messageResource.setUseCodeAsDefaultMessage(true);
        return messageResource;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new CourseFormatter());
        registry.addFormatter(new LectureFormatter());
        registry.addFormatter(new BlogFormatter());
        registry.addFormatter(new SectionFormatter());

    }

    @Override
    public Validator getValidator() {
        return validator();
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean v = new LocalValidatorFactoryBean();
        v.setValidationMessageSource(getMessageResource());
        return v;
    }
}
