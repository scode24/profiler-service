package com.codex.profiler.profilerservice.repository;

import com.codex.profiler.profilerservice.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Modifying
    @Query("delete from Project p where p.idCandidate = :idCandidate")
    int deleteByIdCandidate(long idCandidate);

    @Query("select p.id from Project p where p.idCandidate = :idCandidate")
    List<Long> getProjectIdsByIdCandidate(long idCandidate);

    @Query("select p from Project p where p.idCandidate = :idCandidate")
    List<Project> getProjectsByIdCandidate(long idCandidate);
}
