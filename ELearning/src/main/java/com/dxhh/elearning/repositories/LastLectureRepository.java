package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.LastLecture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LastLectureRepository extends JpaRepository<LastLecture, Integer>, JpaSpecificationExecutor<LastLecture> {
    Optional<LastLecture> findFirstByCourseIdAndUserIdOrderByUpdatedDateDesc(Integer courseId, Integer userId);
}