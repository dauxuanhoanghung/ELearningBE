package com.dxhh.elearning.dto.request;

import com.dxhh.elearning.pojos.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewTransactionRequest {
    private LocalDateTime createdDate;
    private Course course;

    {
        createdDate = LocalDateTime.now();
    }
}
