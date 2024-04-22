package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.pojos.LastLecture;
import com.dxhh.elearning.pojos.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LastLectureRepository extends JpaRepository<LastLecture, Integer>, JpaSpecificationExecutor<LastLecture> {
    Page<LastLecture> findByUserIdOrderByUpdatedDateDesc(Integer userId, Pageable pageable);

    Optional<LastLecture> findByUserAndCourse(User user, Course course);

    Optional<LastLecture> findByCourseId(Integer courseId);
}
