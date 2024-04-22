package com.dxhh.elearning.dto.response;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LastLectureResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -8091879091924046844L;

    private Integer id;
    private LectureResponse lecture;
    private CourseDetailsResponse course;
    private LocalDateTime updatedDate;
}
