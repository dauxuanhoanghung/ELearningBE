package com.dxhh.elearning.services;

import com.dxhh.elearning.dto.request.CourseUpdateRequest;
import com.dxhh.elearning.dto.request.NewCourseRequest;
import com.dxhh.elearning.pojos.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {
    List<Course> findAll(Map<String, String> params);
    List<Course> findRegisteredCourses(Map<String, String> params);
    Course findById(Integer id);
    Course save(NewCourseRequest course);
    Course update(CourseUpdateRequest course);
    boolean deleteById(Integer id);
    Long countRegistrationByCourseId(Integer id);
    Long countLecturesByCourseId(Integer courseId);
    Integer count(Map<String, String> params);
    Integer incrementCourseCount(Integer courseId);
}
