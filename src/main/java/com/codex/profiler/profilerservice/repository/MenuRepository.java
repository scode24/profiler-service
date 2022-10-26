package com.codex.profiler.profilerservice.repository;

import com.codex.profiler.profilerservice.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Modifying
    @Query("delete from Menu m where m.idCandidate = :idCandidate and m.title = :title")
    int deleteByIdCandidate(long idCandidate, String title);

    @Query("select m from Menu m where m.idCandidate = :idCandidate order by m.menuOrder")
    List<Menu> findByIdCandidate(long idCandidate);
}
