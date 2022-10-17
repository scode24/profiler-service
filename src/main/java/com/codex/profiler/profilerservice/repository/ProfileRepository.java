package com.codex.profiler.profilerservice.repository;

import com.codex.profiler.profilerservice.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Candidate, Long> {
    @Query("select c from Candidate c where c.email = :email")
    Candidate findByEmail(String email);
}
