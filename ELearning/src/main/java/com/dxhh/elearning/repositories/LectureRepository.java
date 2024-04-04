package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.pojos.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer>, JpaSpecificationExecutor<Lecture> {
    List<Lecture> findBySectionCourse(Course course);
    List<Lecture> findBySectionCourse_Id(Integer courseId);
    Long countLecturesBySection_Course_Id(Integer courseId);
    List<Lecture> findBySection_Id(Integer sectionId);
    Optional<Lecture> findByOrderIndexAndSection_OrderIndexAndSection_Course_Id(Integer orderIndex, Integer sectionIndex, Integer courseId);
}
