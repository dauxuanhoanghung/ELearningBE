package com.dxhh.elearning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailsResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046851L;
    private Integer id;
    private String name;
    private String background;
    private Double price;
    private List<?> criterias;
    private List<?> sections;
    private Long countRegistration;
}
