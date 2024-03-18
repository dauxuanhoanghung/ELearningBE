package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.RefreshToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer>,
        JpaSpecificationExecutor<RefreshToken> {
}
