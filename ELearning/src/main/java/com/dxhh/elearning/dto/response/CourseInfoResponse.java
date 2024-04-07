package com.dxhh.elearning.dto.response;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseInfoResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -8091879091924046850L;
    private Integer id;
    private String name;
    private String description;
    private String background;
    private Double price;
    private Integer countRegistration;
    private UserResponse user;
}
