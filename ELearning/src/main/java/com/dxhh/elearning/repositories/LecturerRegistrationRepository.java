package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.LecturerRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LecturerRegistrationRepository extends JpaRepository<LecturerRegistration, Integer>,
        JpaSpecificationExecutor<LecturerRegistration> {
    Optional<LecturerRegistration> findByUser_Id(Integer id);
}
