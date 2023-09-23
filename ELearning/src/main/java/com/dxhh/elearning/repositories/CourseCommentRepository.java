package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.CourseComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseCommentRepository extends JpaRepository<CourseComment, Integer> {
    Page<CourseComment> findByCourse_Id(Integer courseId, Pageable pageable);
}
