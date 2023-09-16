package com.dxhh.elearning.validator;

import com.dxhh.elearning.dto.request.UserRegisterRequest;
import com.dxhh.elearning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ExistingMailValidator implements Validator {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegisterRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegisterRequest userRegisterDTO = (UserRegisterRequest) target;
        if (this.userService.loadUserByEmail(userRegisterDTO.getEmail()).size() > 0) {
            errors.rejectValue("email", "validate.email.exists");
        }
    }
}