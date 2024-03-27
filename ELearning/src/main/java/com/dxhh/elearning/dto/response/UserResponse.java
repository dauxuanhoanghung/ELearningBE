package com.dxhh.elearning.dto.response;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -8091879091924046852L;
    private Long id;
    private String username;
    private String avatar;
    private String email;
    private String firstName;
    private String lastName;
    private String slug;
    private List<?> roles;
}
