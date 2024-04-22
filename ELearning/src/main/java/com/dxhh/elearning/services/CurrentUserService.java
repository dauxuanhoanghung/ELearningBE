package com.dxhh.elearning.services;

import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.repositories.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public abstract class CurrentUserService {
    protected final UserRepository userRepository;

    public CurrentUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get current user
    protected User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        List<User> users = this.userRepository.findByUsername(authentication.getName());
        if (users.isEmpty())
            return null;
        return users.get(0);
    }
}
