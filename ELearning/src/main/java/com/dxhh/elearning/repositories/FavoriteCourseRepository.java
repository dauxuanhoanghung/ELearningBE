package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.pojos.FavoriteCourse;
import com.dxhh.elearning.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteCourseRepository extends JpaRepository<FavoriteCourse, Integer> {
    List<FavoriteCourse> findByUser(User user);
    Optional<FavoriteCourse> findByUserAndCourse(User user, Course course);
}
