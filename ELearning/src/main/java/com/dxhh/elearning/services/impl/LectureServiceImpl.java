package com.dxhh.elearning.services.impl;
import com.dxhh.elearning.pojos.Lecture;
import com.dxhh.elearning.repositories.LectureRepository;
import com.dxhh.elearning.services.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class LectureServiceImpl implements LectureService {
    private final LectureRepository lectureRepository;

    @Autowired
    public LectureServiceImpl(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public Lecture create(Lecture lecture) {

        return lectureRepository.save(lecture);
    }

    public Lecture getById(Integer id) {
        return lectureRepository.findById(id).get();
    }

    public Lecture update(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    public boolean deleteById(Integer id) {
        try {
            lectureRepository.deleteById(id);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    public List<Lecture> getByCourseId(Integer courseId) {
        return lectureRepository.findBySectionCourse_Id(courseId);
    }
}
