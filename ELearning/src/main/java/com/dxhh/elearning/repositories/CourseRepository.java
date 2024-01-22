package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.pojos.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>, JpaSpecificationExecutor<Course> {
    Page<Course> findAll(Pageable pageable);
    Page<Course> findByCreator_Id(Integer creator, Pageable pageable);
    Optional<Course> findById(Integer courseId);
}
