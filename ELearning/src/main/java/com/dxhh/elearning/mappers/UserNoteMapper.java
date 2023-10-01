package com.dxhh.elearning.mappers;

import com.dxhh.elearning.dto.response.UserNoteResponse;
import com.dxhh.elearning.pojos.UserNote;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserNoteMapper {
    private final ModelMapper modelMapper;

    public UserNoteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserNoteResponse toResponse(UserNote userNote) {
        return modelMapper.map(userNote, UserNoteResponse.class);
    }
}
