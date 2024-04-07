package com.dxhh.elearning.services;

import com.dxhh.elearning.dto.request.UserRegisterRequest;
import com.dxhh.elearning.pojos.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService {
    List<User> findAll(Map<String, String> params);
    List<User> loadUserByEmail(String email);
    List<User> getUserByUsername(String username);
    List<User> getTopLecturers(int top);
    User save(UserRegisterRequest userRegister);
    User findOneByUsername(String username);
    User update(User user, UserRegisterRequest request);
    boolean existsByUsername(String username);
    boolean deleteById(Integer id);
    boolean deleteCurrent();
    Integer count(Map<String, String> params);
}
