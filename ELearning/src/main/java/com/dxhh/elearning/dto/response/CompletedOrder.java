package com.dxhh.elearning.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompletedOrder {
    private String status;
    private String payId;
    private Integer courseId;
    private String payeeEmail;

    public CompletedOrder(String status) {
        this.status = status;
    }
}
