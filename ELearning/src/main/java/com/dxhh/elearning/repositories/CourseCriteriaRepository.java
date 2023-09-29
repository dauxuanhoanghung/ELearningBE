package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.pojos.CourseCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseCriteriaRepository extends JpaRepository<CourseCriteria, Integer> {
    List<CourseCriteria> findByCourse_Id(Integer course_id);
}
