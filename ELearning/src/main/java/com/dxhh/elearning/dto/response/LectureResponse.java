package com.dxhh.elearning.dto.response;

import com.dxhh.elearning.enums.LectureType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectureResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046851L;
    private Integer id;
    private String title;
    private String content;
    private String description;
    private Integer duration;
    private LectureType type;
    private String videoUrl;
    private int orderIndex;
}
