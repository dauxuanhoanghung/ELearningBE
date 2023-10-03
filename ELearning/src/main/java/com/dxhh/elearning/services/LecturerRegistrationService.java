package com.dxhh.elearning.services;

import com.dxhh.elearning.pojos.LecturerRegistration;

import java.util.List;

public interface LecturerRegistrationService {
    List<LecturerRegistration> getAll();
    LecturerRegistration getById(Integer id);
    LecturerRegistration create(LecturerRegistration registration);
    void delete(Integer id);
}
