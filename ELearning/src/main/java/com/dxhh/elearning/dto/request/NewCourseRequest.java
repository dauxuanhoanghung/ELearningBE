package com.dxhh.elearning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewCourseRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 5926468583005150709L;
    private String name;
    private String subtitle;
    private String description;
    private Double price;
    private MultipartFile backgroundFile;
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime publishDate;
    private LocalDateTime createdDate;
    private List<String> criteria;

    {
        publishDate = LocalDateTime.now();
        createdDate = LocalDateTime.now();
    }
}
