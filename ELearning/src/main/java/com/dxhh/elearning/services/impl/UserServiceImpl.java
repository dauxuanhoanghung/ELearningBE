package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.dto.request.UserRegisterRequest;
import com.dxhh.elearning.mappers.UserMapper;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.repositories.UserRepository;
import com.dxhh.elearning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public List<?> loadUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public List<?> getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public User save(UserRegisterRequest userRegister) {
        User user = userMapper.toUser(userRegister);
        user.setPassword(passwordEncoder.encode(userRegister.getPassword()));
        return this.userRepository.save(user);
    }
}
