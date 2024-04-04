package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.pojos.Progress;
import com.dxhh.elearning.repositories.ProgressRepository;
import com.dxhh.elearning.services.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository repository;

    @Autowired
    public ProgressServiceImpl(ProgressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Progress save(Progress progress) {
        return repository.save(progress);
    }

    @Override
    public Optional<Progress> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Progress> findAll(Map<String, String> params) {
        return repository.findAll();
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
