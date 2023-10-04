package com.dxhh.elearning.services;

import com.dxhh.elearning.dto.request.LecturerRegistrationRequest;
import com.dxhh.elearning.pojos.LecturerRegistration;

import java.util.List;

public interface LecturerRegistrationService {
    List<LecturerRegistration> getAll();
    LecturerRegistration getById(Integer id);
    LecturerRegistration create(LecturerRegistrationRequest registration);
    LecturerRegistration getByCurrentUser();
    void delete(Integer id);
}
