package com.codex.profiler.profilerservice.repository;

import com.codex.profiler.profilerservice.entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface QualificationRepository extends JpaRepository<Qualification, Long> {

    @Modifying
    @Query("delete from Qualification q where q.idCandidate = :idCandidate")
    int deleteByIdCandidate(long idCandidate);

    List<Qualification> findByIdCandidate(long idCandidate);
}
