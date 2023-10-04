package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.dto.request.LecturerRegistrationRequest;
import com.dxhh.elearning.pojos.LecturerRegistration;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.repositories.LecturerRegistrationRepository;
import com.dxhh.elearning.repositories.UserRepository;
import com.dxhh.elearning.services.CloudinaryService;
import com.dxhh.elearning.services.LecturerRegistrationService;
import com.dxhh.elearning.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LecturerRegistrationServiceImpl implements LecturerRegistrationService {

    private final LecturerRegistrationRepository lecturerRegistrationRepository;
    private final UserRepository userRepository;
    private final CloudinaryService cloudinaryService;
    private final Utils utils;

    @Autowired
    public LecturerRegistrationServiceImpl(LecturerRegistrationRepository lecturerRegistrationRepository, UserRepository userRepository, CloudinaryService cloudinaryService, Utils utils) {
        this.lecturerRegistrationRepository = lecturerRegistrationRepository;
        this.userRepository = userRepository;
        this.cloudinaryService = cloudinaryService;
        this.utils = utils;
    }

    // Get current user
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        List<User> users = this.userRepository.findByUsername(authentication.getName());
        if (users.isEmpty())
            return null;
        return users.get(0);
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
    public LecturerRegistration create(LecturerRegistrationRequest request) {
        LecturerRegistration registration = new LecturerRegistration();
        registration.setUser(getCurrentUser());
        if (utils.isNotEmptyFile(request.getFile())) {
            String url = cloudinaryService.uploadImage(request.getFile());
            registration.setImageUrl(url);
        }
        return lecturerRegistrationRepository.save(registration);
    }

    @Override
    public LecturerRegistration getByCurrentUser() {
        User u = getCurrentUser();
        return lecturerRegistrationRepository.findByUser_Id(u.getId()).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        lecturerRegistrationRepository.deleteById(id);
    }

}
