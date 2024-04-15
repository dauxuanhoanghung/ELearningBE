package com.dxhh.elearning.dto.response.stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountUserByRoleResponse {
    private Integer id;
    private String roleName;
    private Long countUser;
}
