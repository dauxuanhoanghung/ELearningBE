package com.dxhh.elearning.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;

public interface UserService extends UserDetailsService {
    List<?> loadUserByEmail(String email);

    List<?> getUserByUsername(String username);
}
