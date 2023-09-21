package com.dxhh.elearning.services;

import com.dxhh.elearning.dto.request.UserRegisterRequest;
import com.dxhh.elearning.pojos.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<?> loadUserByEmail(String email);

    List<?> getUserByUsername(String username);

    User save(UserRegisterRequest userRegister);

}
