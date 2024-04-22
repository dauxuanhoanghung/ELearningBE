package com.dxhh.elearning.dto.response;

import com.dxhh.elearning.enums.LectureType;
import com.dxhh.elearning.pojos.Lecture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectureResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -8091879091924046851L;
    private Integer id;
    private String title;
    private String content;
    private String description;
    private Integer duration;
    private LectureType type;
    private String videoUrl;
    private int orderIndex;

    public LectureResponse(Lecture lecture) {
        this.id = lecture.getId();
        this.title = lecture.getTitle();
        this.content = lecture.getContent();
        this.description = lecture.getDescription();
        this.duration = lecture.getDuration();
        this.type = lecture.getType();
        this.videoUrl = lecture.getVideoUrl();
        this.orderIndex = lecture.getOrderIndex();
    }
}
