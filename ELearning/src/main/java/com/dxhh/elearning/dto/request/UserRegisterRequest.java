package com.dxhh.elearning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 5926468583005150708L;
    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String email;
    private MultipartFile avatarFile = null;
    private String avatar;
    private LocalDateTime createdDate;

    {
        this.createdDate = LocalDateTime.now();
    }
}
