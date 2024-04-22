package com.dxhh.elearning.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompletedOrder {
    private String status;
    private String payId;

    public CompletedOrder(String status) {
        this.status = status;
    }
}
