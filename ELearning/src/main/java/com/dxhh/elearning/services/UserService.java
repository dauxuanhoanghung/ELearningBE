package com.dxhh.elearning.services;

import com.dxhh.elearning.dto.request.UserRegisterRequest;
import com.dxhh.elearning.mappers.UserMapper;
import com.dxhh.elearning.pojos.Role;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.pojos.UserRole;
import com.dxhh.elearning.repositories.UserRepository;
import com.dxhh.elearning.repositories.UserRoleRepository;
import com.dxhh.elearning.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final CloudinaryService cloudinaryService;
    private final Utils utils;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, CloudinaryService cloudinaryService, Utils utils) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.cloudinaryService = cloudinaryService;
        this.utils = utils;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public List<User> loadUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public List<User> getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public User save(UserRegisterRequest userRegister) {
        User user = userMapper.toModel(userRegister);
        user.setPassword(passwordEncoder.encode(userRegister.getPassword()));
        if (utils.isNotEmptyFile(userRegister.getAvatarFile())) {
            String url = cloudinaryService.uploadImage(userRegister.getAvatarFile());
            user.setAvatar(url);
        }
        else {
            user.setAvatar(null);
        }
        Set<UserRole> userRoles = new HashSet<>();
        Role defaultRole = new Role(2, "ROLE_USER");
        userRoles.add(new UserRole(defaultRole, user));
        user.setUserRoles(userRoles);
        User savedUser = userRepository.save(user);

        return userRepository.findById(savedUser.getId()).get();
    }

}
