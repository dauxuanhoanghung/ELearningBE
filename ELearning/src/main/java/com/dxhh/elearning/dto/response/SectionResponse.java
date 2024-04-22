package com.dxhh.elearning.dto.response;

import com.dxhh.elearning.pojos.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectionResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -8091879091924046851L;
    private Integer id;
    private String name;
    private int orderIndex;
    private List<LectureResponse> lectures;

    public SectionResponse(Section section) {
        this.id = section.getId();
        this.name = section.getName();
        this.orderIndex = section.getOrderIndex();
        this.lectures = section.getLectures().stream()
                .map(LectureResponse::new)
                .collect(Collectors.toList());
    }
}
