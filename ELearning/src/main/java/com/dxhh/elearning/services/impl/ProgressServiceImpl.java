package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.pojos.Progress;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.repositories.ProgressRepository;
import com.dxhh.elearning.repositories.UserRepository;
import com.dxhh.elearning.services.CurrentUserService;
import com.dxhh.elearning.services.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ProgressServiceImpl extends CurrentUserService implements ProgressService {

    private final ProgressRepository repository;

    @Autowired
    public ProgressServiceImpl(UserRepository userRepository,
                               ProgressRepository repository) {
        super(userRepository);
        this.repository = repository;
    }

    @Override
    public Progress save(Progress progress) {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            throw new AccessDeniedException("You don't have permission to save this resource");
        }

        progress.setUser(currentUser);
        return repository.save(progress);
    }

    @Override
    public Optional<Progress> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Progress> findAll(Map<String, String> params) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return repository.findByUserUsernameAndCourseId(authentication.getName(), Integer.parseInt(params.get("courseId")));
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Progress updateDone(Integer id, boolean done) {
        Optional<Progress> optionalProgress = repository.findById(id);
        if (optionalProgress.isPresent()) {
            Progress progress = optionalProgress.get();
            progress.setDone(done);
            return repository.save(progress);
        } else {
            // Handle the case when the progress is not found
            throw new IllegalArgumentException("Progress with id " + id + " not found");
        }
    }
}
