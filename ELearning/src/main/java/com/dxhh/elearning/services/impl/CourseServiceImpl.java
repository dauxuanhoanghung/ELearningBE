package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.dto.request.NewCourseRequest;
import com.dxhh.elearning.mappers.CourseMapper;
import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.pojos.CourseCriteria;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.repositories.CourseRepository;
import com.dxhh.elearning.repositories.LectureRepository;
import com.dxhh.elearning.repositories.TransactionRepository;
import com.dxhh.elearning.repositories.UserRepository;
import com.dxhh.elearning.services.CloudinaryService;
import com.dxhh.elearning.services.CourseService;
import com.dxhh.elearning.services.CurrentUserService;
import com.dxhh.elearning.specifications.GSpecification;
import com.dxhh.elearning.specifications.SearchCriteria;
import com.dxhh.elearning.specifications.SearchOperation;
import com.dxhh.elearning.utils.Utils;
import jakarta.persistence.criteria.Predicate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseServiceImpl extends CurrentUserService implements CourseService {

    private final CourseRepository courseRepository;
    private final TransactionRepository courseRegistrationRepository;
    private final LectureRepository lectureRepository;
    private final CloudinaryService cloudinaryService;
    private final CourseMapper courseMapper;
    private final Utils utils;
    private final Environment env;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,
                             UserRepository userRepository,
                             CloudinaryService cloudinaryService,
                             TransactionRepository courseRegistrationRepository,
                             LectureRepository lectureRepository,
                             CourseMapper courseMapper,
                             Utils utils,
                             Environment env) {
        super(userRepository);
        this.courseRepository = courseRepository;
        this.courseRegistrationRepository = courseRegistrationRepository;
        this.cloudinaryService = cloudinaryService;
        this.lectureRepository = lectureRepository;
        this.courseMapper = courseMapper;
        this.utils = utils;
        this.env = env;
    }


    @Override
    @Cacheable(cacheNames = "course.list")
    public List<Course> findAll(Map<String, String> params) {
        int page = Integer.parseInt(params.get("page"));
        int pageNumber = Math.max(page, 0);
        int size = env.getProperty("SIZE", Integer.class, 8);
        if (params.containsKey("pageSize")) {
            size = Integer.parseInt(params.get("pageSize"));
        }
        List<Course> course;
        Specification<Course> specification = toSpecification(params);

        Pageable pageable = PageRequest.of(pageNumber, size);
        course = this.courseRepository.findAll(specification, pageable).getContent();
        return course;
    }

    @Override
    public List<Course> findRegisteredCourses() {
        User user = getCurrentUser();

        List course = courseRegistrationRepository.findAll();
        return null;
    }

    @Override
    @Cacheable(cacheNames = "course.id", key = "#id")
    public Course findById(Integer id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        return courseOptional.orElse(null);
    }

    @Override
    public Course save(NewCourseRequest courseRequest) {
        Course newCourse = courseMapper.toModel(courseRequest);

        Set<CourseCriteria> criteria = new HashSet<>();
        List<String> criteriaList = courseRequest.getCriteria();
        for (String s : criteriaList) {
            CourseCriteria cri = new CourseCriteria();
            cri.setCourse(newCourse);
            cri.setText(s);
            criteria.add(cri);
        }
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
    public Long countRegistrationByCourseId(Integer courseId) {
        return courseRegistrationRepository.countByCourse_Id(courseId);
    }

    @Override
    public Long countLecturesByCourseId(Integer courseId) {
        return lectureRepository.countLecturesBySection_Course_Id(courseId);
    }

    @Override
    @Cacheable(cacheNames = "course.count")
    public Integer count(Map<String, String> params) {
        Specification<Course> specification = toSpecification(params);
        return Integer.valueOf(courseRepository.count(specification) + "");
    }

    @Override
    public Integer incrementCourseCount(Integer courseId) {
        return courseRepository.incrementCourseCount(courseId);
    }

    private Specification<Course> toSpecification(Map<String, String> params) {
        List<SearchCriteria> criteriaList = new ArrayList<>();

        if (params.containsKey("name")) {
            String nameValue = params.get("name");
            SearchCriteria nameCriteria = new SearchCriteria("name", SearchOperation.LIKE, nameValue);
            SearchCriteria descriptionCriteria = new SearchCriteria("description", SearchOperation.LIKE, nameValue);

            List<GSpecification<Course>> orSpecifications = Arrays.asList(
                    new GSpecification<>(nameCriteria),
                    new GSpecification<>(descriptionCriteria)
            );
            criteriaList.add(new SearchCriteria("", SearchOperation.OR, orSpecifications));
        }

        if (params.containsKey("min")) {
            criteriaList.add(new SearchCriteria("price", SearchOperation.GREATER_THAN_OR_EQUAL, Double.parseDouble(params.get("min"))));
        }

        if (params.containsKey("max")) {
            criteriaList.add(new SearchCriteria("price", SearchOperation.LESS_THAN_OR_EQUAL, Double.parseDouble(params.get("max"))));
        }

        if (params.containsKey("username")) {
            criteriaList.add(new SearchCriteria("creator.username", SearchOperation.EQUAL, params.get("username")));
        }

        if (params.containsKey("slug")) {
            criteriaList.add(new SearchCriteria("creator.slug", SearchOperation.EQUAL, params.get("slug")));
        }

        if (params.containsKey("creator_id")) {
            criteriaList.add(new SearchCriteria("creator.id", SearchOperation.EQUAL, Integer.parseInt(params.get("creator_id"))));
        }

        if (params.containsKey("business")) {
            criteriaList.add(new SearchCriteria("creator.id", SearchOperation.EQUAL, Objects.requireNonNull(getCurrentUser()).getId()));
        }

        if (params.containsKey("learning") && !Boolean.parseBoolean(params.get("learning"))) {
            criteriaList.add(new SearchCriteria("creator.id", SearchOperation.NOT_EQUAL, Objects.requireNonNull(getCurrentUser()).getId()));
        }

        criteriaList.add(SearchCriteria.builder()
                .key("publishDate")
                .operation(SearchOperation.LESS_THAN_OR_EQUAL)
                .value(LocalDateTime.now())
                .build());

        return GSpecification.toSpecification(criteriaList);
    }
}

