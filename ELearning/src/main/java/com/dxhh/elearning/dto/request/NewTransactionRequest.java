package com.dxhh.elearning.dto.request;

import com.dxhh.elearning.pojos.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewTransactionRequest {
    private LocalDateTime createdDate;
    private Course course;
    private BigDecimal amount = BigDecimal.valueOf(0);
    // username of receiver
    private String username;

    {
        createdDate = LocalDateTime.now();
    }
}
