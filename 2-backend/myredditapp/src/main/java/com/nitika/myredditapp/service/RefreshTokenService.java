package com.nitika.myredditapp.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nitika.myredditapp.entity.RefreshToken;
import com.nitika.myredditapp.exception.MyRedditException;
import com.nitika.myredditapp.repository.RefreshTokenRepository;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreationDate(Instant.now());

        return refreshTokenRepository.save(refreshToken);
    }

    void validateRefreshToken(String token) {
        refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new MyRedditException("Invalid refresh Token"));
    }

    public void deleteRefreshToken(String token) {
		System.out.println("-------> Niti: Inside RefreshTokenService-> deleteRefreshToken");
        refreshTokenRepository.deleteByToken(token);
        System.out.println("-------> Niti: Inside RefreshTokenService-> After deleting token");
    }
}