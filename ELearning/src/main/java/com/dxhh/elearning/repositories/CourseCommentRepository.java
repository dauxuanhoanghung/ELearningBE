package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.CourseComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseCommentRepository extends JpaRepository<CourseComment, Integer>, JpaSpecificationExecutor<CourseComment> {

}
