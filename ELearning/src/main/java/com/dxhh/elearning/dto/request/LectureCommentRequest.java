package com.dxhh.elearning.dto.request;

import com.dxhh.elearning.pojos.Blog;
import com.dxhh.elearning.pojos.Lecture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureCommentRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 5926468583005150737L;
    private String content;
    private Date createdDate;
    private Lecture lecture;

    {
        createdDate = Date.from(Instant.now());
    }

}