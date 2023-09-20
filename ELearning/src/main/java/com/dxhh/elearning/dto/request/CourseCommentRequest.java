package com.dxhh.elearning.dto.request;

import com.dxhh.elearning.pojos.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseCommentRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150717L;
    private String text;
    private Date createdDate;
    {
        createdDate = Date.from(Instant.now());
    }
    private Course courseId;

}
