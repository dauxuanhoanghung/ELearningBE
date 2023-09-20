package com.dxhh.elearning.dto.request;

import com.dxhh.elearning.pojos.Blog;
import com.dxhh.elearning.pojos.Lecture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectureCommentRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150737L;
    private String text;
    private Date createdDate;
    private Lecture lectureId;

    {
        createdDate = Date.from(Instant.now());
    }

}