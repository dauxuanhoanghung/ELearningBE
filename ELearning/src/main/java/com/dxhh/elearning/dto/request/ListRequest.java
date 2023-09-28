package com.dxhh.elearning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150709L;
    private List<NewSectionRequest> sections;
}
