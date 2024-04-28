package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.dto.request.NewLectureRequest;
import com.dxhh.elearning.enums.LectureType;
import com.dxhh.elearning.enums.UploaderType;
import com.dxhh.elearning.mappers.LectureMapper;
import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.pojos.Lecture;
import com.dxhh.elearning.repositories.LectureRepository;
import com.dxhh.elearning.services.AmazonS3Service;
import com.dxhh.elearning.services.LectureService;
import com.dxhh.elearning.services.YoutubeService;
import com.dxhh.elearning.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
@Transactional
public class LectureServiceImpl implements LectureService {
    private final AmazonS3Service amazonS3Service;
    private final YoutubeService youtubeService;
    private final LectureRepository lectureRepository;
    private final LectureMapper lectureMapper;
    private final Utils utils;

    @Autowired
    public LectureServiceImpl(AmazonS3Service amazonS3Service, YoutubeService youtubeService, LectureRepository lectureRepository, LectureMapper lectureMapper, Utils utils) {
        this.amazonS3Service = amazonS3Service;
        this.youtubeService = youtubeService;
        this.lectureRepository = lectureRepository;
        this.lectureMapper = lectureMapper;
        this.utils = utils;
    }

    @Override
    public Lecture create(NewLectureRequest lectureRequest) {
        Lecture lecture = lectureMapper.toModel(lectureRequest);
        if (lectureRequest.getType().equals(LectureType.VIDEO)) {
            if (utils.isVideoFile(lectureRequest.getVideoFile())) {
                String url = null;
                File file = utils.multipartToFile(lectureRequest.getVideoFile());
                if (lectureRequest.getUploaderType().equals(UploaderType.YOUTUBE)) {
                    url = youtubeService.uploadFile(file, lectureRequest.getTitle(), lectureRequest.getContent());
                } else if (lectureRequest.getUploaderType().equals(UploaderType.AMAZONS3)) {
                    url = amazonS3Service.uploadFile(file);
                }
                lecture.setVideoUrl(url);
            }
        }
        return lectureRepository.save(lecture);
    }

    @Override
    public Lecture getById(Integer id) {
        return lectureRepository.findById(id).orElse(null);
    }

    @Override
    public Lecture getById(Integer id, Integer courseId) {
        boolean isLectureInCourse = lectureRepository.existsByCourseAndLecture(new Course(courseId), new Lecture(id));
        if (!isLectureInCourse)
            throw new IllegalArgumentException("Lecture not found in course");

        return lectureRepository.findById(id).orElse(null);
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

    @Override
    public List<Lecture> getBySectionId(Integer sectionId) {
        return lectureRepository.findBySection_Id(sectionId);
    }

    @Override
    public Lecture getFirstLectureOfCourse(Integer courseId) {
        return lectureRepository.findByOrderIndexAndSection_OrderIndexAndSection_Course_Id(1, 1, courseId).orElse(null);
    }
}
