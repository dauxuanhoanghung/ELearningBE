package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.CourseComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseCommentRepository extends JpaRepository<CourseComment, Integer> {
}
