package com.dxhh.elearning.services;

import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.pojos.FavoriteCourse;
import com.dxhh.elearning.pojos.User;

import java.util.List;
import java.util.Optional;

public interface FavoriteCourseService {
    Optional<FavoriteCourse> getByCourseId(Integer id);
    List<FavoriteCourse> getByUser();

    FavoriteCourse toggleFavorite(Course course);
}
