package com.dxhh.elearning.services;

import com.dxhh.elearning.dto.request.UserRegisterRequest;
import com.dxhh.elearning.pojos.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService {
    List<User> loadUserByEmail(String email);
    List<User> getUserByUsername(String username);
    User save(UserRegisterRequest userRegister);
    boolean existsByUsername(String username);
    User findOneByUsername(String username);
    User update(User user, UserRegisterRequest request);
    boolean deleteById(Integer id);
    boolean deleteCurrent();
    Integer count(Map<String, String> params);
    List<User> findAll(Map<String, String> params);
}
