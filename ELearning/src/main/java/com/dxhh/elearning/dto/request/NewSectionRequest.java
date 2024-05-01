package com.dxhh.elearning.dto.request;

import com.dxhh.elearning.pojos.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewSectionRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 5926468583005150709L;
    private Integer id;
    private String name;
    private Integer orderIndex;
    private Course course;
}
