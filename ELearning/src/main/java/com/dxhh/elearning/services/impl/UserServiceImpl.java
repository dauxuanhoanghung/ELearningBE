package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService  {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public List<?> loadUserByEmail(String email) {
        return null;
    }

    @Override
    public List<?> getUserByUsername(String username) {
        return null;
    }
}
