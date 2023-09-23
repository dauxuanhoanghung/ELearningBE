package com.dxhh.elearning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewBlogRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150709L;
    private String title;
    private String content;
    private LocalDateTime createdDate;

    {
        createdDate = LocalDateTime.now();
    }
}
