package com.dxhh.elearning.services;

import com.dxhh.elearning.pojos.CourseCriteria;

import java.util.List;

public interface CourseCriteriaService {
    List<CourseCriteria> getByCourseId(Integer courseId);
    CourseCriteria save();
}
