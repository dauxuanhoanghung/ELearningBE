package com.dxhh.elearning.mappers;

import com.dxhh.elearning.dto.request.UserRegisterRequest;
import com.dxhh.elearning.dto.response.UserResponse;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.pojos.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    private ModelMapper mapper;
    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.mapper = modelMapper;
    }

    public User toModel(UserRegisterRequest userRegister) {
        User user = mapper.map(userRegister, User.class);
        return user;
    }
    public UserResponse toResponse(User user) {
        UserResponse res = mapper.map(user, UserResponse.class);
        List l = new ArrayList<>();
        for (UserRole ur : user.getUserRoles()) {
            l.add(ur.getRole().getName());
        }
        res.setRoles(l);
        return res;
    }
}
