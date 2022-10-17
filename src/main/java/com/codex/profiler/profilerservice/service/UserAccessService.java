package com.codex.profiler.profilerservice.service;

import com.codex.profiler.profilerservice.entity.Candidate;
import com.codex.profiler.profilerservice.model.UserInfoModel;
import com.codex.profiler.profilerservice.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

@Service
public class UserAccessService {

    @Autowired
    private CandidateRepository candidateRepository;

    public boolean isUserExists(UserInfoModel userInfoModel) {
        Candidate candidate = candidateRepository.findByEmail(userInfoModel.getEmail());
        return candidate != null;
    }

    public String addUserAndGetToken(UserInfoModel userInfoModel) throws UnsupportedEncodingException {

        String token = String.valueOf(UUID.randomUUID());
        String encodedToken = Base64.getEncoder().encodeToString(token.getBytes());
        candidateRepository.save(new Candidate(userInfoModel.getName(), userInfoModel.getEmail(), null, encodedToken));
        return token;
    }

    private String decodeToken(String accessToken) throws UnsupportedEncodingException {
        return new String(Base64.getDecoder().decode(accessToken.getBytes()));
    }
}
