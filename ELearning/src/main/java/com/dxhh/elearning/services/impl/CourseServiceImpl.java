package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.dto.request.NewCourseRequest;
import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.services.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Override
    public List<Course> findCourses(Map<String, String> params) {
        return null;
    }

    @Override
    public Course findCourseById(Integer id) {
        return null;
    }

    @Override
    public Course save(NewCourseRequest course) {
        return null;
    }

    @Override
    public Course update(Course course) {
        return null;
    }
}
