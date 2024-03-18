package com.dxhh.elearning.services;

import com.dxhh.elearning.pojos.RefreshToken;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken save();
    Optional<RefreshToken> findById(Integer id);
    List<RefreshToken> findAll();
    void delete(Integer id);
}
