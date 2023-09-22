package com.dxhh.elearning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class UserResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046852L;
    private Long id;
    private String username;
    private String password;
    private String avatar;
    private String email;
    private String firstName;
    private String lastName;
    private List<?> roles;
}
