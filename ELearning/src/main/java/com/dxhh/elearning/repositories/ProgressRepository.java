package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.Progress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository

public interface ProgressRepository extends JpaRepository<Progress, Integer>,
        JpaSpecificationExecutor<Progress> {
}
