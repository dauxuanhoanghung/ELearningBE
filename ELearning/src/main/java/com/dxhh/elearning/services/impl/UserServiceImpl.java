package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.dto.request.UserRegisterRequest;
import com.dxhh.elearning.mappers.UserMapper;
import com.dxhh.elearning.pojos.Role;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.pojos.UserRole;
import com.dxhh.elearning.repositories.UserRepository;
import com.dxhh.elearning.repositories.UserRoleRepository;
import com.dxhh.elearning.services.CloudinaryService;
import com.dxhh.elearning.services.UserService;
import com.dxhh.elearning.specifications.GSpecification;
import com.dxhh.elearning.specifications.SearchCriteria;
import com.dxhh.elearning.specifications.SearchOperation;
import com.dxhh.elearning.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final CloudinaryService cloudinaryService;
    private final Utils utils;
    private final DateTimeFormatter formatter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserRoleRepository userRoleRepository,
                           UserMapper userMapper,
                           PasswordEncoder passwordEncoder,
                           CloudinaryService cloudinaryService,
                           Utils utils,
                           DateTimeFormatter formatter) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.cloudinaryService = cloudinaryService;
        this.utils = utils;
        this.formatter = formatter;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            List<User> users = this.getUserByUsername(username);
            if (users.isEmpty())
                return null;
            return users.get(0);
        } catch (Exception e) {
            return null;
        }
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
        } else {
            user.setAvatar(null);
        }
        Set<UserRole> userRoles = new HashSet<>();
        Role defaultRole = new Role(2, "ROLE_USER");
        userRoles.add(new UserRole(defaultRole, user));
        user.setUserRoles(userRoles);
        User savedUser = userRepository.save(user);

        return userRepository.findById(savedUser.getId()).get();
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User findOneByUsername(String username) {
        List<User> users = userRepository.findByUsername(username);
        if (users.isEmpty())
            return null;
        else
            return users.get(0);
    }

    public User update(User user, UserRegisterRequest request) {
        if (utils.isNotEmptyFile(request.getAvatarFile())) {
            String url = cloudinaryService.uploadImage(request.getAvatarFile());
            user.setAvatar(url);
        }
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        return userRepository.save(user);
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Cacheable(cacheNames = "user.count")
    public Integer count(Map<String, String> params) {
        try {
            List<SearchCriteria> criteriaList = new ArrayList<>();
            if (params.containsKey("startDate")) {
                LocalDateTime startDate = LocalDateTime.parse(params.get("startDate") + " 00:00:00", formatter);
                criteriaList.add(new SearchCriteria("createdDate", SearchOperation.GREATER_THAN_OR_EQUAL, startDate));
            }

            if (params.containsKey("endDate")) {
                LocalDateTime endDate = LocalDateTime.parse(params.get("endDate") + " 00:00:00", formatter);
                criteriaList.add(new SearchCriteria("createdDate", SearchOperation.LESS_THAN_OR_EQUAL, endDate));
            }

            return Math.toIntExact(userRepository.count(GSpecification.toSpecification(criteriaList)));
        } catch (Exception e) {
            return 0;
        }
    }
}
