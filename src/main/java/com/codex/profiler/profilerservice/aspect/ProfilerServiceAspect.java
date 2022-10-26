package com.codex.profiler.profilerservice.aspect;

import com.codex.profiler.profilerservice.entity.Candidate;
import com.codex.profiler.profilerservice.exception.InvalidInfoException;
import com.codex.profiler.profilerservice.repository.CandidateRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;

@Aspect
@Component
public class ProfilerServiceAspect {

    @Autowired
    CandidateRepository candidateRepository;

    @Before(value = "execution(* com.codex.profiler.profilerservice.controller.ProfileController.saveOrUpdate*(..))")
    public void checkUserAccess() throws InvalidInfoException, UnsupportedEncodingException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String accessToken = request.getHeader("access-token");

        if (accessToken == null)
            throw new InvalidInfoException("access-token must be provided as header parameter");

        String encodedToken = Base64.getEncoder().encodeToString(accessToken.getBytes());
        String email = request.getQueryString().trim().replace("email=", "");
        email = URLDecoder.decode(email, "UTF-8");
        
        Candidate candidate = candidateRepository.findByEmailAndToken(email, encodedToken);

        if (candidate == null)
            throw new InvalidInfoException("Invalid input. Access denied.");
    }
}
