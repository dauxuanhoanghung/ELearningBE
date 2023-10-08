package com.dxhh.elearning.dto.response.stats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountUserAndMonthResponse {
    private String month;
    private Long countUser;
}
