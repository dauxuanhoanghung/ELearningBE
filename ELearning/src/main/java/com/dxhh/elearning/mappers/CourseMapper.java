package com.dxhh.elearning.mappers;

import com.dxhh.elearning.dto.request.NewCourseRequest;
import com.dxhh.elearning.dto.response.CourseDetailsResponse;
import com.dxhh.elearning.dto.response.CourseInfoResponse;
import com.dxhh.elearning.pojos.Course;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    private ModelMapper mapper;
    @Autowired
    public CourseMapper(ModelMapper modelMapper) {
        this.mapper = modelMapper;
    }

    public Course toModel(NewCourseRequest courseRequest) {
        Course course = mapper.map(courseRequest, Course.class);
        return course;
    }

    public CourseInfoResponse toInfo(Course course) {
        CourseInfoResponse res = mapper.map(course, CourseInfoResponse.class);
        return res;
    }
    public CourseDetailsResponse toDetail(Course course) {
        CourseDetailsResponse res = mapper.map(course, CourseDetailsResponse.class);
        return res;
    }
}
