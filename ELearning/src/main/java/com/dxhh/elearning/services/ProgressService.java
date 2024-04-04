package com.dxhh.elearning.services;

import com.dxhh.elearning.pojos.Progress;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProgressService {
    Progress save(Progress progress);
    Optional<Progress> findById(Integer id);
    List<Progress> findAll(Map<String, String> params);
    void deleteById(Integer id);
    Progress updateDone(Integer id, boolean done);
}
