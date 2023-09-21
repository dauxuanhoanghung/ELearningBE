package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.FavoriteCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteCourseRepository extends JpaRepository<FavoriteCourse, Integer> {
}
