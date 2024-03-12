package com.dxhh.elearning.validators;

import com.dxhh.elearning.dto.request.UserRegisterRequest;
import com.dxhh.elearning.services.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class ExistingUsernameValidator implements Validator {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegisterRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegisterRequest userRegisterRequest = (UserRegisterRequest) target;
        if (!this.userService.getUserByUsername(userRegisterRequest.getUsername()).isEmpty()) {
            errors.rejectValue("username", "validator.username.exists");
        }
    }
}