package com.dxhh.elearning.validator;

import com.dxhh.elearning.dto.request.UserRegisterRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ConfirmPasswordValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegisterRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegisterRequest userRegisterRequest = (UserRegisterRequest) target;
        if (userRegisterRequest.getConfirmPassword() != null &&
                userRegisterRequest.getPassword() != null &&
                !userRegisterRequest.getPassword().equals(userRegisterRequest.getConfirmPassword())) {
            errors.rejectValue("rePassword", "validate.rePassword.notMatch");
        }
    }
}
