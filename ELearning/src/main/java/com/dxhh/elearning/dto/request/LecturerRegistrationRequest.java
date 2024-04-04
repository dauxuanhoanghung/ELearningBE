package com.dxhh.elearning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LecturerRegistrationRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 5926468583005150709L;
    private Integer id;
    private MultipartFile file;
}
