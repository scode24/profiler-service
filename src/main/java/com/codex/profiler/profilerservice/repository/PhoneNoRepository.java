package com.codex.profiler.profilerservice.repository;

import com.codex.profiler.profilerservice.entity.PhoneNo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface PhoneNoRepository extends JpaRepository<PhoneNo, Long> {

    @Modifying
    @Query("delete from PhoneNo a where a.idCandidate = :idCandidate")
    int deleteByIdCandidate(long idCandidate);

    List<PhoneNo> findByIdCandidate(long idCandidate);
}
