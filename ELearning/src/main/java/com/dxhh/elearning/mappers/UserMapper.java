package com.dxhh.elearning.mappers;

import com.dxhh.elearning.dto.request.UserRegisterRequest;
import com.dxhh.elearning.pojos.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private ModelMapper mapper;
    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.mapper = modelMapper;
    }

    public User toUser(UserRegisterRequest userRegister) {
        User user = mapper.map(userRegister, User.class);
        return user;
    }
}
