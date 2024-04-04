package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.Lecture;
import com.dxhh.elearning.pojos.LectureComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureCommentRepository extends JpaRepository<LectureComment, Integer>
        , JpaSpecificationExecutor<LectureComment> {
    Page<LectureComment> findByLecture(Lecture lecture, Pageable pageable);
}
