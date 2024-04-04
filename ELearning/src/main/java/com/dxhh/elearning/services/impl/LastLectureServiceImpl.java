package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.pojos.LastLecture;
import com.dxhh.elearning.repositories.LastLectureRepository;
import com.dxhh.elearning.services.LastLectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LastLectureServiceImpl implements LastLectureService {

    private final LastLectureRepository lastLectureRepository;

    @Autowired
    public LastLectureServiceImpl(LastLectureRepository lastLectureRepository) {
        this.lastLectureRepository = lastLectureRepository;
    }

    @Override
    public LastLecture saveOrUpdate(LastLecture lastLecture) {
        return lastLectureRepository.save(lastLecture);
    }

    @Override
    public Optional<LastLecture> findById(Integer id) {
        return lastLectureRepository.findById(id);
    }

    @Override
    public Optional<LastLecture> findLastLectureForCourseAndUser(Integer courseId, Integer userId) {
        return lastLectureRepository.findFirstByCourseIdAndUserIdOrderByUpdatedDateDesc(courseId, userId);

    }

}