package com.codex.profiler.profilerservice.repository;

import com.codex.profiler.profilerservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Modifying
    @Query("delete from Address a where a.idCandidate = :idCandidate")
    int deleteByIdCandidate(long idCandidate);

    List<Address> findByIdCandidate(long idCandidate);
}
