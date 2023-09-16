package com.dxhh.elearning.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ModelResponse {
    private Object data;
    private Long status;

}
