package com.dxhh.elearning.dto.response.stats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountUserByRoleResponse {
    private Integer id;
    private String roleName;
    private Long countUser;
}
