package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.pojos.LecturerRegistration;
import com.dxhh.elearning.repositories.LecturerRegistrationRepository;
import com.dxhh.elearning.services.CloudinaryService;
import com.dxhh.elearning.services.LecturerRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LecturerRegistrationServiceImpl implements LecturerRegistrationService {

    private final LecturerRegistrationRepository lecturerRegistrationRepository;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public LecturerRegistrationServiceImpl(LecturerRegistrationRepository lecturerRegistrationRepository, CloudinaryService cloudinaryService) {
        this.lecturerRegistrationRepository = lecturerRegistrationRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public List<LecturerRegistration> getAll() {
        return lecturerRegistrationRepository.findAll();
    }

    @Override
    public LecturerRegistration getById(Integer id) {
        return lecturerRegistrationRepository.findById(id).orElse(null);
    }

    @Override
    public LecturerRegistration create(LecturerRegistration registration) {
        return lecturerRegistrationRepository.save(registration);
    }

    @Override
    public void delete(Integer id) {
        lecturerRegistrationRepository.deleteById(id);
    }

}
