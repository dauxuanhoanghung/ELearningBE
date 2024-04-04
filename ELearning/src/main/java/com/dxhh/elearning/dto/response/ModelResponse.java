package com.dxhh.elearning.dto.response;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -8091879091924046845L;
    private Integer status;
    private String message;
    private Object data;

}
