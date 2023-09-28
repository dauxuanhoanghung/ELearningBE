package com.dxhh.elearning.dto.request;

import com.dxhh.elearning.pojos.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewSectionRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150709L;
    private String sectionName;
    private Integer orderIndex;
    private List<NewLectureRequest> lectures;
    private Course course;
}
