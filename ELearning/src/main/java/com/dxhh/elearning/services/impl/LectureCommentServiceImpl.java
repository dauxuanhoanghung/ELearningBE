package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.dto.request.LectureCommentRequest;
import com.dxhh.elearning.pojos.Lecture;
import com.dxhh.elearning.pojos.LectureComment;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.repositories.LectureCommentRepository;
import com.dxhh.elearning.repositories.UserRepository;
import com.dxhh.elearning.services.CurrentUserService;
import com.dxhh.elearning.services.LectureCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class LectureCommentServiceImpl extends CurrentUserService implements LectureCommentService {
    private final LectureCommentRepository lectureCommentRepository;

    @Autowired
    public LectureCommentServiceImpl(LectureCommentRepository lectureCommentRepository, UserRepository userRepository) {
        super(userRepository);
        this.lectureCommentRepository = lectureCommentRepository;
    }

    @Override
    public LectureComment save(LectureCommentRequest request) {
        LectureComment lectureComment = new LectureComment();
        lectureComment.setContent(request.getContent());
        lectureComment.setCreatedDate(LocalDateTime.now());
        lectureComment.setLecture(request.getLecture());
        lectureComment.setUser(getCurrentUser());
        return lectureCommentRepository.save(lectureComment);
    }

    @Override
    public LectureComment getById(Integer id) {
        return lectureCommentRepository.findById(id).orElse(null);
    }

    @Override
    public LectureComment update(LectureComment lectureComment) {
        return lectureCommentRepository.save(lectureComment);
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            lectureCommentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<LectureComment> getByLecture(Lecture lecture) {
//        return lectureCommentRepository.findByLecture(lecture);
        return null;
    }

    @Override
    public List<LectureComment> getByLectureId(Integer lectureId, int page) {
        int pageNumber = Math.max(page, 0);

        Pageable pageable = PageRequest.of(pageNumber, 10);
        return lectureCommentRepository.findByLecture(new Lecture(lectureId), pageable).getContent();
    }
}
