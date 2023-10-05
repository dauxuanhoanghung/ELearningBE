package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.pojos.Role;
import com.dxhh.elearning.pojos.UserRole;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.repositories.UserRepository;
import com.dxhh.elearning.repositories.UserRoleRepository;
import com.dxhh.elearning.services.UserRoleService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, UserRepository userRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
    }
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        List<User> users = this.userRepository.findByUsername(authentication.getName());
        if (users.isEmpty())
            return null;
        return users.get(0);
    }

    @Override
    public UserRole add(User user) {
        return userRoleRepository.save(new UserRole(new Role(3), user));
    }
}
