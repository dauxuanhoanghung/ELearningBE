package com.dxhh.elearning.services;

import com.dxhh.elearning.pojos.LastLecture;

import java.util.Optional;

public interface LastLectureService {
    LastLecture saveOrUpdate(LastLecture lastLecture);
    Optional<LastLecture> findById(Integer id);
    Optional<LastLecture> findLastLectureForCourseAndUser(Integer courseId, Integer userId);

}
