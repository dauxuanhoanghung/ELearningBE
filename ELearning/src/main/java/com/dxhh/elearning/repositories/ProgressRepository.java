package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.Progress;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProgressRepository extends JpaRepository<Progress, Integer>,
        JpaSpecificationExecutor<Progress> {
    @EntityGraph(attributePaths = {
            "lecture",
            "lecture.section",
            "lecture.section.course",
            "user"
    })
    @Query("SELECT p FROM Progress p WHERE p.lecture.section.course.id = :courseId AND p.user.username = :username")
    List<Progress> findByUserUsernameAndCourseId(@Param("username") String username, @Param("courseId") Integer courseId);
}
