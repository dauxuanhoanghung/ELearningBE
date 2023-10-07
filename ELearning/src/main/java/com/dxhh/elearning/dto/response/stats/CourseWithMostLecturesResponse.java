package com.dxhh.elearning.dto.response.stats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseWithMostLecturesResponse {
    private Integer id;
    private String name;
    private Long countLectures;
}
