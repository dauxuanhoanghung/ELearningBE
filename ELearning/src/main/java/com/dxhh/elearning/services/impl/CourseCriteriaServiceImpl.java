package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.pojos.CourseCriteria;
import com.dxhh.elearning.repositories.CourseCriteriaRepository;
import com.dxhh.elearning.services.CourseCriteriaService;
import com.dxhh.elearning.specifications.GSpecification;
import com.dxhh.elearning.specifications.SearchCriteria;
import com.dxhh.elearning.specifications.SearchOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseCriteriaServiceImpl implements CourseCriteriaService {
    private final CourseCriteriaRepository courseCriteriaRepository;

    public CourseCriteriaServiceImpl(CourseCriteriaRepository courseCriteriaRepository) {
        this.courseCriteriaRepository = courseCriteriaRepository;
    }

    @Override
    @Cacheable(cacheNames = "criteria.list", key = "courseId")
    public List<CourseCriteria> getByCourseId(Integer courseId) {
        Specification<CourseCriteria> specification = new GSpecification<>(
                new SearchCriteria("course.id", SearchOperation.EQUAL, courseId)
        );

        return courseCriteriaRepository.findAll(specification);
    }

    @Override
    public CourseCriteria save() {
        return null;
    }
}
