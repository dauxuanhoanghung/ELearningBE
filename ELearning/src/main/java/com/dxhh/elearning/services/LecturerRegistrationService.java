package com.dxhh.elearning.services;

import com.dxhh.elearning.dto.request.LecturerRegistrationRequest;
import com.dxhh.elearning.pojos.LecturerRegistration;

import java.util.List;
import java.util.Map;

public interface LecturerRegistrationService {
    List<LecturerRegistration> getForm(Map<String, String> params);
    LecturerRegistration getById(Integer id);
    LecturerRegistration create(LecturerRegistrationRequest registration);
    LecturerRegistration getByCurrentUser();
    void delete(Integer id);
}
