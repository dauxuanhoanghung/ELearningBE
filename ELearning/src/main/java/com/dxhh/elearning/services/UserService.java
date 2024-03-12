package com.dxhh.elearning.services;

import com.dxhh.elearning.dto.request.UserRegisterRequest;
import com.dxhh.elearning.pojos.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public List<User> loadUserByEmail(String email);
    public List<User> getUserByUsername(String username);
    public User save(UserRegisterRequest userRegister);
    public boolean existsByUsername(String username);
    public User findOneByUsername(String username);
    public User update(User user, UserRegisterRequest request);
}
