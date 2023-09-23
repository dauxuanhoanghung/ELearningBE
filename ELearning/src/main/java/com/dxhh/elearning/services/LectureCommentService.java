package com.dxhh.elearning.services;

import com.dxhh.elearning.pojos.Lecture;
import com.dxhh.elearning.pojos.LectureComment;

import java.util.List;
import java.util.Optional;

public interface LectureCommentService {

    LectureComment save(LectureComment lectureComment);
    LectureComment getById(Integer id);
    LectureComment update(LectureComment lectureComment);
    boolean deleteById(Integer id);
    List<LectureComment> getByLecture(Lecture lecture);
    List<LectureComment> getByLectureId(Integer id);

}

