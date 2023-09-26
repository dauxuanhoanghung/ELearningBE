package com.dxhh.elearning.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150708L;
    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String email;
    private MultipartFile avatarFile;
    private String avatar;
    private LocalDateTime createdDate;

    {
        this.createdDate = LocalDateTime.now();
    }
}
