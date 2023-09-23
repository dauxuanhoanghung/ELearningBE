package com.dxhh.elearning.services;

import com.dxhh.elearning.pojos.Lecture;

import java.util.List;

public interface LectureService {
    Lecture create(Lecture lecture);
    Lecture getById(Integer id);
    Lecture update(Lecture lecture);
    boolean deleteById(Integer id);
    List<Lecture> getByCourseId(Integer courseId);
}
