package com.codex.profiler.profilerservice.repository;

import com.codex.profiler.profilerservice.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    @Query("select c from Candidate c where c.id = :idCandidate")
    Candidate findByIdCandidate(long idCandidate);

    @Query("select c from Candidate c where c.email = :email")
    Candidate findByEmail(String email);

    @Query("select c from Candidate c where c.email = :email and accessToken = :accessToken")
    Candidate findByEmailAndToken(String email, String accessToken);
}
