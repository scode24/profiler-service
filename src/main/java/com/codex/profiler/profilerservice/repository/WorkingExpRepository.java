package com.codex.profiler.profilerservice.repository;

import com.codex.profiler.profilerservice.entity.WorkingExp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface WorkingExpRepository extends JpaRepository<WorkingExp, Long> {

    @Modifying
    @Query("delete from WorkingExp w where w.idCandidate = :idCandidate")
    int deleteByIdCandidate(long idCandidate);

    List<WorkingExp> findByIdCandidate(long idCandidate);
}
