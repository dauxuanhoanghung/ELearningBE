package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.services.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
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
    public Course save(Course course) {
        return null;
    }

    @Override
    public Course update(Course course) {
        return null;
    }
}
