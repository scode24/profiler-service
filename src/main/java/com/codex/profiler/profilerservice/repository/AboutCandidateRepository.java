package com.codex.profiler.profilerservice.repository;

import com.codex.profiler.profilerservice.entity.AboutCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface AboutCandidateRepository extends JpaRepository<AboutCandidate, Long> {

    @Modifying
    @Query("delete from AboutCandidate a where a.idCandidate = :idCandidate")
    int deleteByIdCandidate(long idCandidate);

    @Query("select ac from AboutCandidate ac where ac.idCandidate = :idCandidate")
    AboutCandidate findByIdCandidate(long idCandidate);
}
