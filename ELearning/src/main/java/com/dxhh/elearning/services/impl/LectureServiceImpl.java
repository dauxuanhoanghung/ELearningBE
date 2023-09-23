package com.dxhh.elearning.services.impl;
import com.dxhh.elearning.pojos.Lecture;
import com.dxhh.elearning.repositories.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class LectureServiceImpl {
    private final LectureRepository lectureRepository;

    @Autowired
    public LectureServiceImpl(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public Lecture createLecture(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    public Lecture getById(Integer id) {
        return lectureRepository.findById(id).get();
    }

    public Lecture update(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    public void deleteById(Integer id) {
        lectureRepository.deleteById(id);
    }

    public List<Lecture> getAll() {
        return lectureRepository.findAll();
    }
}
