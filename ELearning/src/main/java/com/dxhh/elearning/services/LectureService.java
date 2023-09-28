package com.dxhh.elearning.services;

import com.dxhh.elearning.dto.request.NewLectureRequest;
import com.dxhh.elearning.pojos.Lecture;

import java.util.List;

public interface LectureService {
    Lecture create(NewLectureRequest lecture);
    Lecture getById(Integer id);
    Lecture update(Lecture lecture);
    boolean deleteById(Integer id);
    List<Lecture> getByCourseId(Integer courseId);
}
