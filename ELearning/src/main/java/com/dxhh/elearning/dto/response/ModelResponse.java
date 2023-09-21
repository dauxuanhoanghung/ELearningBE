package com.dxhh.elearning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046845L;
    private Integer status;
    private String message;
    private Object data;

}
