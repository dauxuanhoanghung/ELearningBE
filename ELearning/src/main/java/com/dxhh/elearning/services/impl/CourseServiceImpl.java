package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.dto.request.NewCourseRequest;
import com.dxhh.elearning.mappers.CourseMapper;
import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.repositories.CourseRepository;
import com.dxhh.elearning.repositories.TransactionRepository;
import com.dxhh.elearning.services.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final TransactionRepository courseRegistrationRepository;
    private final CourseMapper courseMapper;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, TransactionRepository courseRegistrationRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseRegistrationRepository = courseRegistrationRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public List<Course> findCourses(Map<String, String> params) {

        // return courseRepository.findBySomeCriteria(params);
        return null; // Replace with actual implementation
    }

    @Override
    public Course findById(Integer id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        return courseOptional.orElse(null);
    }

    @Override
    public Course save(NewCourseRequest course) {
        Course newCourse = courseMapper.toModel(course);
        newCourse.setName(course.getName());
        newCourse.setDescription(course.getDescription());
//        newCourse.setBackground(course.getBackground());
        newCourse.setPrice(course.getPrice());
//        newCourse.setCreatorId(course.get());
        newCourse.setPublishDate(course.getPublishDate());
        newCourse.setCreatedDate(course.getCreatedDate());

        return courseRepository.save(newCourse);
    }

    @Override
    public Course update(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            courseRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Long countRegistrationById(Integer id) {
        return courseRegistrationRepository.countByCourseId(id);
    }
}

