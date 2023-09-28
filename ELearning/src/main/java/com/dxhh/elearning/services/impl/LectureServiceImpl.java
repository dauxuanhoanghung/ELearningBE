package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.dto.request.NewLectureRequest;
import com.dxhh.elearning.enums.LectureType;
import com.dxhh.elearning.mappers.LectureMapper;
import com.dxhh.elearning.pojos.Lecture;
import com.dxhh.elearning.repositories.LectureRepository;
import com.dxhh.elearning.services.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LectureServiceImpl implements LectureService {
    private final LectureRepository lectureRepository;
    private final LectureMapper lectureMapper;

    @Autowired
    public LectureServiceImpl(LectureRepository lectureRepository, LectureMapper lectureMapper) {
        this.lectureRepository = lectureRepository;
        this.lectureMapper = lectureMapper;
    }

    @Override
    public Lecture create(NewLectureRequest lectureRequest) {
        Lecture lecture = lectureMapper.toModel(lectureRequest);
        if (lectureRequest.getType().equals(LectureType.VIDEO)) {

        }
        return lectureRepository.save(lecture);
    }

    @Override
    public Lecture getById(Integer id) {
        return lectureRepository.findById(id).get();
    }

    @Override
    public Lecture update(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            lectureRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<Lecture> getByCourseId(Integer courseId) {
        return lectureRepository.findBySectionCourse_Id(courseId);
    }
}
