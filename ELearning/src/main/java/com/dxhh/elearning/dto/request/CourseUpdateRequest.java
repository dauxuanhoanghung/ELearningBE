package com.dxhh.elearning.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseUpdateRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 5926468583005150709L;
    private Integer id;
    private String name;
    private String subtitle;
    private String description;
    private Double price;
    private MultipartFile backgroundFile;
    private LocalDateTime publishDate;
    private List<String> criteria;
    private List<String> sections;
}
