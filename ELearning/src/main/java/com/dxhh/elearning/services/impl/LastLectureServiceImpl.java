package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.pojos.LastLecture;
import com.dxhh.elearning.pojos.Transaction;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.repositories.LastLectureRepository;
import com.dxhh.elearning.repositories.TransactionRepository;
import com.dxhh.elearning.repositories.UserRepository;
import com.dxhh.elearning.services.CurrentUserService;
import com.dxhh.elearning.services.LastLectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class LastLectureServiceImpl extends CurrentUserService implements LastLectureService {

    private final LastLectureRepository lastLectureRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public LastLectureServiceImpl(LastLectureRepository lastLectureRepository,
                                  TransactionRepository transactionRepository,
                                  UserRepository userRepository) {
        super(userRepository);
        this.lastLectureRepository = lastLectureRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public LastLecture save(LastLecture lastLecture) {
        User user = getCurrentUser();

        // Check if the user has registered for the course
        boolean isUserRegistered = transactionRepository.existsByUserAndCourse(user, lastLecture.getCourse());
        if (!isUserRegistered) {
            return null;
        }

        LastLecture existingOne = lastLectureRepository.findByUserAndCourse(user, lastLecture.getCourse())
                .orElseGet(LastLecture::new);
        lastLecture.setUser(user);
        lastLecture.setLecture(lastLecture.getLecture());
        lastLecture.setUpdatedDate(LocalDateTime.now());
        if (existingOne.getId() != null) {
            lastLecture.setId(existingOne.getId());
        }
        return lastLectureRepository.save(lastLecture);
    }

    @Override
    public LastLecture findByCourseId(Integer courseId) {
        return lastLectureRepository.findByCourseId(courseId).orElse(null);
    }

    @Override
    public List<LastLecture> findListByCurrentUser(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        User user = getCurrentUser();
        return lastLectureRepository.findByUserIdOrderByUpdatedDateDesc(user.getId(), pageable).getContent();
    }

}