package com.dxhh.elearning.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150708L;

    private String username;
    private String password;
    private String confirmPassword;
    private String email;
}
