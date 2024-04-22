package com.dxhh.elearning.mappers;

import com.dxhh.elearning.dto.response.LastLectureResponse;
import com.dxhh.elearning.pojos.LastLecture;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LastLectureMapper {
    private final ModelMapper mapper;

    public LastLectureMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public LastLectureResponse toResponse(LastLecture lastLecture) {
        return mapper.map(lastLecture, LastLectureResponse.class);
    }
}
