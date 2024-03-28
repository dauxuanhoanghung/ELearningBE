package com.dxhh.elearning.mappers;

import com.dxhh.elearning.dto.request.UserRegisterRequest;
import com.dxhh.elearning.dto.response.UserResponse;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.pojos.UserRole;
import com.dxhh.elearning.repositories.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    private final ModelMapper mapper;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserMapper(ModelMapper modelMapper, UserRoleRepository userRoleRepository) {
        this.mapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
    }

    public User toModel(UserRegisterRequest userRegister) {
        return mapper.map(userRegister, User.class);
    }

    public UserResponse toResponse(User user, boolean showRoles) {
        UserResponse res = UserResponse.builder()
                .id(Long.valueOf(user.getId()))
                .avatar(user.getAvatar())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .slug(user.getSlug())
                .build();

        if (showRoles) {
            List<String> l = new ArrayList<>();
            for (UserRole ur : userRoleRepository.findByUser(user)) {
                l.add(ur.getRole().getName());
            }
            res.setRoles(l);
        }
        return res;
    }

    public UserResponse toResponse(User user) {
        return this.toResponse(user, true);
    }
}
