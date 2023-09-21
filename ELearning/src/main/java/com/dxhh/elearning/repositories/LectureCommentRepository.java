package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.LectureComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureCommentRepository extends JpaRepository<LectureComment, Integer> {
}
