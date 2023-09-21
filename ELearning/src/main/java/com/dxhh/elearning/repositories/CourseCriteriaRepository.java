package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.CourseCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseCriteriaRepository extends JpaRepository<CourseCriteria, Integer> {
}
