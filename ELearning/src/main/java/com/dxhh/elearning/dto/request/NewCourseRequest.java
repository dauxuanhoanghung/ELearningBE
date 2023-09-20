package com.dxhh.elearning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCourseRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150709L;

    private String name;
    private String description;
    private Double price;
    private MultipartFile backgroundFile;
    private Date publishDate;
    private Date createdDate;
    private List<String> criteria;
    private List<String> sections;

    {
        createdDate = Date.from(Instant.now());
    }
}
