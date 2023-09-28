package com.dxhh.elearning.mappers;

import com.dxhh.elearning.dto.request.NewLectureRequest;
import com.dxhh.elearning.pojos.Lecture;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LectureMapper {
    private final ModelMapper modelMapper;

    public LectureMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Lecture toModel(NewLectureRequest request) {
        return modelMapper.map(request, Lecture.class);
    }
}
