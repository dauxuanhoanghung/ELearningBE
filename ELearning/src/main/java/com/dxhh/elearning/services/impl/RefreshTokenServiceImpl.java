package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.pojos.RefreshToken;
import com.dxhh.elearning.repositories.RefreshTokenRepository;
import com.dxhh.elearning.services.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    @Autowired
    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }
    @Override
    public RefreshToken save() {
        RefreshToken token = new RefreshToken();
        return refreshTokenRepository.save(token);
    }

    @Override
    public Optional<RefreshToken> findById(Integer id) {
        return refreshTokenRepository.findById(id);
    }

    @Override
    public List<RefreshToken> findAll() {
        return refreshTokenRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        refreshTokenRepository.deleteById(id);
    }
}
