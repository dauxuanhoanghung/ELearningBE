package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.pojos.Lecture;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.pojos.UserNote;
import com.dxhh.elearning.repositories.UserNoteRepository;
import com.dxhh.elearning.repositories.UserRepository;
import com.dxhh.elearning.services.CurrentUserService;
import com.dxhh.elearning.services.UserNoteService;
import com.dxhh.elearning.specifications.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserNoteServiceImpl extends CurrentUserService implements UserNoteService {
    private final UserNoteRepository userNoteRepository;

    @Autowired
    public UserNoteServiceImpl(UserNoteRepository userNoteRepository, UserRepository userRepository) {
        super(userRepository);
        this.userNoteRepository = userNoteRepository;
    }

    @Override
    public UserNote create(UserNote userNote) {
        userNote.setUser(getCurrentUser());
        return userNoteRepository.save(userNote);
    }

    @Override
    public UserNote getById(Integer id) {
        return userNoteRepository.findById(id).get();
    }

    @Override
    public List<UserNote> findByUser(User user) {
        Specification<UserNote> spec = new GSpecification<>(
                new SearchCriteria("user", SearchOperation.EQUAL, user)
        );
        return userNoteRepository.findAll(spec);
    }

    @Override
    public List<UserNote> findByUserAndLecture(Lecture lecture) {
        User user = getCurrentUser();
        return userNoteRepository.findByUserAndLecture(user, lecture);
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            userNoteRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
