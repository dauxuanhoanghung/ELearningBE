package com.dxhh.elearning.services;

import com.dxhh.elearning.pojos.LastLecture;

import java.util.List;
import java.util.Optional;

public interface LastLectureService {
    LastLecture save(LastLecture lastLecture);

    LastLecture findByCourseId(Integer courseId);

    List<LastLecture> findListByCurrentUser(Integer page, Integer size);
}
