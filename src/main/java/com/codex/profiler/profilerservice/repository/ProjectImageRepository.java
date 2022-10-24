package com.codex.profiler.profilerservice.repository;

import com.codex.profiler.profilerservice.entity.ProjectImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface ProjectImageRepository extends JpaRepository<ProjectImage, Long> {

    @Modifying
    @Query("delete from ProjectImage p where p.idProject in (:idProjects)")
    int deleteByIdProjects(List<Long> idProjects);

    @Query("select p from ProjectImage p where p.idProject = :idProject")
    List<ProjectImage> getProjectImageByIdProject(long idProject);
}
