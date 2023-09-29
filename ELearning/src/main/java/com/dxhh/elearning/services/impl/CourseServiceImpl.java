package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.dto.request.NewCourseRequest;
import com.dxhh.elearning.mappers.CourseMapper;
import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.pojos.CourseCriteria;
import com.dxhh.elearning.pojos.Section;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.repositories.CourseRepository;
import com.dxhh.elearning.repositories.TransactionRepository;
import com.dxhh.elearning.repositories.UserRepository;
import com.dxhh.elearning.services.CloudinaryService;
import com.dxhh.elearning.services.CourseService;
import com.dxhh.elearning.utils.Utils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final TransactionRepository courseRegistrationRepository;
    private final CloudinaryService cloudinaryService;
    private final CourseMapper courseMapper;
    private final Utils utils;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository, CloudinaryService cloudinaryService, TransactionRepository courseRegistrationRepository, CourseMapper courseMapper, Utils utils) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.courseRegistrationRepository = courseRegistrationRepository;
        this.cloudinaryService = cloudinaryService;
        this.courseMapper = courseMapper;
        this.utils = utils;
    }

    // Get current user
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        List<User> users = this.userRepository.findByUsername(authentication.getName());
        if (users.isEmpty())
            return null;
        return users.get(0);
    }

    @Override
    public List<Course> findCourses(Map<String, String> params) {
        return this.courseRepository.findAll();
    }

    @Override
    public Course findById(Integer id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        return courseOptional.orElse(null);
    }

    @Override
    public Course save(NewCourseRequest courseRequest) {
        Course newCourse = courseMapper.toModel(courseRequest);

        Set<CourseCriteria> criteria = courseRequest.getCriteria().stream().map(s -> {
            CourseCriteria cri = new CourseCriteria();
            cri.setCourse(newCourse);
            cri.setText(s);
            return cri;
        }).collect(Collectors.toSet());
        newCourse.setCourseCriterias(criteria);
        newCourse.setCreator(getCurrentUser());
        if (utils.isNotEmptyFile(courseRequest.getBackgroundFile())) {
            String url = cloudinaryService.uploadImage(courseRequest.getBackgroundFile());
            newCourse.setBackground(url);
        }
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

