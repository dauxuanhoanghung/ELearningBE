package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.pojos.Role;
import com.dxhh.elearning.pojos.UserRole;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.repositories.UserRepository;
import com.dxhh.elearning.repositories.UserRoleRepository;
import com.dxhh.elearning.services.CurrentUserService;
import com.dxhh.elearning.services.UserRoleService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserRoleServiceImpl extends CurrentUserService implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, UserRepository userRepository) {
        super(userRepository);
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRole add(User user) {
        return userRoleRepository.save(new UserRole(new Role(3), user));
    }
}
