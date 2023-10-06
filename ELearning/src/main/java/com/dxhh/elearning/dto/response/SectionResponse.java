package com.dxhh.elearning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionResponse {
    private Integer id;
    private String sectionName;
    private int orderIndex;
    private List<LectureResponse> lectures;
}
