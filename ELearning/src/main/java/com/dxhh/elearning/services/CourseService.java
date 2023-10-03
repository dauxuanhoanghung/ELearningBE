package com.dxhh.elearning.services;

import com.dxhh.elearning.dto.request.NewCourseRequest;
import com.dxhh.elearning.pojos.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {
    List<Course> findCourses(Map<String, String> params);
    Course findById(Integer id);
    Course save(NewCourseRequest course);
    Course update(Course course);
    boolean deleteById(Integer id);
    Long countRegistrationByCourseId(Integer id);
    Long countLecturesByCourseId(Integer courseId);
    Long countCourses();
}
