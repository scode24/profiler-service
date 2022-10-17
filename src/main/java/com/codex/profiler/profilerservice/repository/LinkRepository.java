package com.codex.profiler.profilerservice.repository;

import com.codex.profiler.profilerservice.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface LinkRepository extends JpaRepository<Link, Long> {

    @Modifying
    @Query("delete from Link l where l.idCandidate = :idCandidate")
    int deleteByIdCandidate(long idCandidate);

    @Query("select l from Link l where l.idCandidate = :idCandidate")
    List<Link> findByIdCandidate(long idCandidate);
}
