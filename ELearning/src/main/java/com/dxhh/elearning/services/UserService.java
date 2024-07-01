package com.dxhh.elearning.services;

import com.dxhh.elearning.dto.request.UserRegisterRequest;
import com.dxhh.elearning.pojos.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService {
    List<User> findAll(Map<String, String> params);
    List<User> loadUserByEmail(String email);
    List<User> findByUsername(String username);
    List<User> getTopLecturers(int top);
    User save(UserRegisterRequest userRegister);
    User findOneByUsername(String username);
    User update(User user, UserRegisterRequest request);
    boolean existsByUsername(String username);
    boolean deleteById(Integer id);
    boolean deleteCurrent(String password);
    Integer count(Map<String, String> params);

    User changePassword(String password, String newPassword);

    public Double getCreditByUserId(Integer id);
    public User updateCreditByUserId(Integer id, Double credit);

    public Double getCreditByUsername(String username);
    public User updateCreditByUsername(String username, Double credit);
}
