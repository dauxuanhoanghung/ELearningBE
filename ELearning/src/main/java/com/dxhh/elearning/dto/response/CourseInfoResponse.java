package com.dxhh.elearning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseInfoResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046850L;
    private Integer id;
    private String name;
    private String description;
    private Long countRegistration;
}
