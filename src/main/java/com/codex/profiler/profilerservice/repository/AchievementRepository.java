package com.codex.profiler.profilerservice.repository;

import com.codex.profiler.profilerservice.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface AchievementRepository extends JpaRepository<Achievement, Long> {

    @Modifying
    @Query("delete from Achievement a where a.idCandidate = :idCandidate")
    int deleteByIdCandidate(long idCandidate);

    List<Achievement> findByIdCandidate(long idCandidate);
}
