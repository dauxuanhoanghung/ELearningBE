package com.dxhh.elearning.services;

import com.dxhh.elearning.pojos.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {
    List<Course> findCourses(Map<String, String> params);
    Course findCourseById(Integer id);
    Course save(Course course);
    Course update(Course course);
}
