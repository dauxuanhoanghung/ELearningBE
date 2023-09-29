package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.pojos.CourseCriteria;
import com.dxhh.elearning.repositories.CourseCriteriaRepository;
import com.dxhh.elearning.services.CourseCriteriaService;
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
    public List<CourseCriteria> getByCourseId(Integer courseId) {
        return courseCriteriaRepository.findByCourse_Id(courseId);
    }

    @Override
    public CourseCriteria save() {
        return null;
    }
}
