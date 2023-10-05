package com.dxhh.elearning.validators;

import com.dxhh.elearning.dto.request.UserRegisterRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class ConfirmPasswordValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegisterRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegisterRequest userRegisterRequest = (UserRegisterRequest) target;
        if (userRegisterRequest.getConfirmPassword() != null
                && userRegisterRequest.getPassword() != null
                && !userRegisterRequest.getPassword().equals(userRegisterRequest.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "validator.confirmPassword.notMatch");
        }
    }
}
