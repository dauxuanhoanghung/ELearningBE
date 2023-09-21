package com.dxhh.elearning.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Component
public class Utils {
    private final Environment env;

    @Autowired
    public Utils(Environment env) {
        this.env = env;
    }

    public Map<String, String> getAllErrorValidation(BindingResult result) {
        try {
            Map<String, String> errors = new HashMap<>();
            result.getAllErrors().forEach((error) -> {

                String fieldName = ((FieldError) error).getField();
                String message = "";
                if (error.getDefaultMessage() != null) {
                    message = error.getDefaultMessage();
                } else {
                    message = env.getProperty(error.getCode());
                }
                errors.put(fieldName, message);
            });
            return errors;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }
}
