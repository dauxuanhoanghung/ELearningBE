package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.LecturerRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRegistrationRepository extends JpaRepository<LecturerRegistration, Integer> {
}
