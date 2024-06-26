package com.dxhh.elearning.dto.request;

import com.dxhh.elearning.enums.LectureType;
import com.dxhh.elearning.enums.UploaderType;
import com.dxhh.elearning.pojos.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewLectureRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 5926468583005150709L;
    private String title;
    private String content;
    private LectureType type;
    private int orderIndex;
    private Integer duration;
    private Section section;
    UploaderType uploaderType = UploaderType.YOUTUBE;
    private MultipartFile videoFile;
}
