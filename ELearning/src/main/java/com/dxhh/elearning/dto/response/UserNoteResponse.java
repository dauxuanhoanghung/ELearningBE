package com.dxhh.elearning.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserNoteResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046852L;
    private Integer id;
    private String text;
    private Integer noteTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdDate;
}
