package com.codex.profiler.profilerservice.repository;

import com.codex.profiler.profilerservice.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface SkillRepository extends JpaRepository<Skill, Long> {

    @Modifying
    @Query("delete from Skill s where s.idCandidate = :idCandidate")
    int deleteByIdCandidate(long idCandidate);

    List<Skill> findByIdCandidate(long idCandidate);
}
