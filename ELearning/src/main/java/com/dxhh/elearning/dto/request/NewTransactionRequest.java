package com.dxhh.elearning.dto.request;

import com.dxhh.elearning.pojos.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewTransactionRequest {
    private LocalDateTime createdDate;
    private BigDecimal amount = BigDecimal.valueOf(0);
    private String code;
    private String method;
    // username of receiver
    private String username;
    private Course course;


    {
        createdDate = LocalDateTime.now();
    }
}
